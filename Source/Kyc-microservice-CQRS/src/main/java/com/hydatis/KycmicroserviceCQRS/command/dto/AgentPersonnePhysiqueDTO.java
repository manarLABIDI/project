package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class AgentPersonnePhysiqueDTO {

    private Step1 step1;
    private Step2 step2;
    private Step3 step3;
    private Step4 step4;
    private Long id;


}
