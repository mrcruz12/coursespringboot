package br.com.dars.springboot.domain.enums;

public enum PaymentStatus {

    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(3, "Cancelado");


    private int code;
    private String description;

    private PaymentStatus(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer code){
        for (PaymentStatus ct : PaymentStatus.values()){
            if(code.equals(ct.getCode()))
                return ct;
        }
        throw new IllegalArgumentException("Invalid code: "+code);
    }
}
