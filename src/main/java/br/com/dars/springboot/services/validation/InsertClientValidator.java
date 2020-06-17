package br.com.dars.springboot.services.validation;

import br.com.dars.springboot.controller.exceptions.FieldMessage;
import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.domain.enums.ClientType;
import br.com.dars.springboot.dto.NewClientDTO;
import br.com.dars.springboot.repository.ClientRepository;
import br.com.dars.springboot.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class InsertClientValidator implements ConstraintValidator<InsertClient, NewClientDTO> {

    @Autowired
    private ClientRepository clientRepo;

    @Override
    public void initialize(InsertClient constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getClientType().equals(ClientType.NATURALPERSON.getCode()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CPF is not valid"));
        }

        if(objDto.getClientType().equals(ClientType.LEGALPERSON.getCode()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ is not valid"));
        }

        Client c = clientRepo.findByEmail(objDto.getEmail());

        if(c != null ){
            list.add(new FieldMessage("email", "Email exists"));
        }

        for (FieldMessage f : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
