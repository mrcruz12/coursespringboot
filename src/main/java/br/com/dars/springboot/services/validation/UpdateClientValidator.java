package br.com.dars.springboot.services.validation;

import br.com.dars.springboot.controller.exceptions.FieldMessage;
import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.dto.ClientDTO;
import br.com.dars.springboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepo;

    @Override
    public void initialize(UpdateClient constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client c = clientRepo.findByEmail(objDto.getEmail());

        if(c != null && !c.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email exists"));
        }

        for (FieldMessage f : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
