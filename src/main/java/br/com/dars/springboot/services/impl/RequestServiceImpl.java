package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.PaymentSlip;
import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.domain.RequestItem;
import br.com.dars.springboot.domain.enums.PaymentStatus;
import br.com.dars.springboot.repository.PaymentRepository;
import br.com.dars.springboot.repository.RequestItemRepository;
import br.com.dars.springboot.repository.RequestRepository;
import br.com.dars.springboot.services.ClientService;
import br.com.dars.springboot.services.EmailService;
import br.com.dars.springboot.services.ProductService;
import br.com.dars.springboot.services.RequestService;
import br.com.dars.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private SlipServiceImpl slipService;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestItemRepository requestItemRepo;

    @Override
    public Request findById(Long id) {
        Optional<Request> obj = requestRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found Object! ID: "+id+", Tipo: "+Request.class.getName()));
    }

    @Transactional
    @Override
    public Request save(Request request) {
        request.setId(null);
        request.setInstant(new Date());
        request.setClient(clientService.findById(request.getClient().getId()));
        request.getPayment().setPaymentStatus(PaymentStatus.PENDING);
        request.getPayment().setRequest(request);

        if(request.getPayment() instanceof PaymentSlip){
            PaymentSlip paymentSlip = (PaymentSlip) request.getPayment();
            slipService.fillPaymentSplit(paymentSlip, request.getInstant());
        }
        request = requestRepo.save(request);
        paymentRepo.save(request.getPayment());

        for (RequestItem r : request.getRequestItems()){
            r.setProduct(productService.findById(r.getProduct().getId()));
            r.setDiscount(0.0);
            r.setPrice(r.getProduct().getPrice());
            r.setRequest(request);
        }
        requestItemRepo.saveAll(request.getRequestItems());
        emailService.sendOrderConfirmationEmailHtml(request);
        return request;
    }

    @Override
    public List<Request> findAll() {
        return requestRepo.findAll();
    }
}
