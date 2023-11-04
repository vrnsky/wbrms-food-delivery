package my.edu.sunway.wbrms.wbrmsfooddelivery.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.sunway.wbrms.wbrmsfooddelivery.DatabaseIntegrationTest;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryType;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.SearchRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class DeliveryControllerTest extends DatabaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Tag("negative")
    @DisplayName("Test case: Creation delivery with empty customer name is prohibited")
    void testThatDeliveryWithBlankCustomerNameIsNotCreating() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "",
                BigDecimal.TEN,
                null,
                DeliveryType.DELIVERY,
                "foodpanda",
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        mockMvc.perform(post("/create")
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Tag("negative")
    @DisplayName("Test case: Creation delivery without delivery type is prohibited")
    void testThatDeliveryWithNullDeliverTypeIsNotPossible() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "Egor Voronianskii",
                BigDecimal.TEN,
                null,
                null,
                "foodpanda",
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        mockMvc.perform(post("/create")
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Tag("negative")
    @DisplayName("Test case: Creation delivery without delivery type is prohibited")
    void testThatDeliveryWithoutDeliveryPartnerIsNotPossible() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "Egor Voronianskii",
                BigDecimal.TEN,
                null,
                DeliveryType.DELIVERY,
                null,
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        mockMvc.perform(post("/create")
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Tag("negative")
    @DisplayName("Test case: Creation delivery without delivery type is prohibited")
    void testThatDeliveryWithInvalidDeliveryPartnerIsNotPossible() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "Egor Voronianskii",
                BigDecimal.TEN,
                null,
                DeliveryType.DELIVERY,
                "helicopter",
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        mockMvc.perform(post("/create")
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Tag("negative")
    @DisplayName("Test case: Update not existing delivery")
    void testThatServiceNotUpdatingNotExistingDelivery() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "Egor Voronianskii",
                BigDecimal.TEN,
                null,
                DeliveryType.DELIVERY,
                "foodpanda",
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        var response = mockMvc.perform(put("/update/" + UUID.randomUUID())
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();

        var problemDetail = objectMapper.readValue(response.getResponse().getContentAsString(), ProblemDetail.class);
        Assertions.assertNotNull(problemDetail);
        Assertions.assertTrue(StringUtils.isNotBlank(problemDetail.getTitle()));
        Assertions.assertTrue(StringUtils.isNotBlank(problemDetail.getDetail()));
    }

    @Test
    @Tag("negative")
    @DisplayName("Test case: Delete not existing delivery")
    void testThatDeleteOfNotExistingDeliveryIsNotPossible() throws Exception {
        var response = mockMvc.perform(delete("/cancel/" + UUID.randomUUID()))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();

        var problemDetail = objectMapper.readValue(response.getResponse().getContentAsString(), ProblemDetail.class);
        Assertions.assertNotNull(problemDetail);
        Assertions.assertTrue(StringUtils.isNotBlank(problemDetail.getTitle()));
        Assertions.assertTrue(StringUtils.isNotBlank(problemDetail.getDetail()));
    }

    @Test
    @Tag("positive")
    @DisplayName("Test case: Searching deliveries by parameters")
    void testThatSearchingDeliveryWorksProperly() throws Exception {
        var delivery = new Delivery(
                null,
                null,
                "Egor Voronianskii",
                BigDecimal.TEN,
                null,
                DeliveryType.DELIVERY,
                "foodpanda",
                LocalDateTime.now().plusMinutes(30),
                null,
                null
        );

        mockMvc.perform(post("/create")
                        .content(objectMapper.writeValueAsBytes(delivery))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andDo(print());

        var searchRequest = new SearchRequest(
                null,
                null,
                delivery.customer(),
                delivery.amount(),
                null,
                delivery.partner(),
                null,
                null,
                null,
                null
        );

        var controllerResponse = mockMvc.perform(post("/search")
                        .content(objectMapper.writeValueAsBytes(searchRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        List<Delivery> deliveries = objectMapper.readValue(controllerResponse.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertNotNull(deliveries);
        Assertions.assertFalse(deliveries.isEmpty());
        Assertions.assertEquals(1, deliveries.size());
    }
}