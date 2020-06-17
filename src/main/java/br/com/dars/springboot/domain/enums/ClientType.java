package br.com.dars.springboot.domain.enums;

public enum ClientType {
    NATURALPERSON(1, "Pessoa Física"),
    LEGALPERSON(2, "Pessoa Jurídica");

    private int code;
    private String description;

    private ClientType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer code){
        for (ClientType ct : ClientType.values()){
            if(code.equals(ct.getCode()))
                return ct;
        }
        throw new IllegalArgumentException("Invalid code: "+code);
    }
}
