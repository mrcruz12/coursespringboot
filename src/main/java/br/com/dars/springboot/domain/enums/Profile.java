package br.com.dars.springboot.domain.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");


    private int code;
    private String description;

    private Profile(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer code){
        for (Profile ct : Profile.values()){
            if(code.equals(ct.getCode()))
                return ct;
        }
        throw new IllegalArgumentException("Invalid code: "+code);
    }
}
