package DGU_AI_LAB.admin_be.domain.requests.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "answers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String question;
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonBackReference
    private Request request;
}