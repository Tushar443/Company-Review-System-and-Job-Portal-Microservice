package com.project.CompanyMicroservice.dto.RabbitMQ;

public class JobMessage {
    private Long jobId;
    private Long companyId;
    private String operation;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        System.out.println("job id = "+jobId + " company id = "+ companyId);
        return super.toString();
    }
}
