package hailingservice.domain;

import hailingservice.domain.*;
import hailingservice.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RequstPaymentCompleted extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String paymentId;
    private Integer price;
    private String name;
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
}
