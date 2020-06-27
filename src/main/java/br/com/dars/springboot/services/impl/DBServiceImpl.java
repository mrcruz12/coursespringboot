package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.*;
import br.com.dars.springboot.domain.enums.ClientType;
import br.com.dars.springboot.domain.enums.PaymentStatus;
import br.com.dars.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBServiceImpl {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private EstateRepository estateRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RequestItemRepository requestItemRepo;

    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 300.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));


        categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estate e1 = new Estate(null, "São Paulo");
        Estate e2 = new Estate(null, "Minas Gerais");

        City city1 = new City(null, "São José dos Campos", e1);
        City city2 = new City(null, "Uberlandia", e2);
        City city3 = new City(null, "Caçapava", e1);

        e1.getCities().addAll(Arrays.asList(city1, city3));
        e2.getCities().addAll(Arrays.asList(city2));

        estateRepo.saveAll(Arrays.asList(e1, e2));

        cityRepo.saveAll(Arrays.asList(city1, city2, city3));

        Client cli1 = new Client(null, "Ananda N A da Cruz", "darstecnologia@gmail.com", "12332112345", ClientType.NATURALPERSON);
        cli1.getFones().addAll(Arrays.asList("12982223441", "12982223442"));

        Address address1 = new Address(null, "Rua das Chácaras", "85", "Apto 38 bloco A2", "Jardim Oriente", "12236080", cli1, city1);
        Address address2 = new Address(null, "Rua das Chácaras", "85", "condominio Terras do Vale", "Jardim da Grama", "12233550", cli1, city3);

        cli1.getAddresses().addAll(Arrays.asList(address1, address2));
        clientRepo.saveAll(Arrays.asList(cli1));

        addressRepo.saveAll(Arrays.asList(address1, address2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Request req1 = new Request(null, sdf.parse("30/09/2019 10:32"), cli1, address1);
        Request req2 = new Request(null, sdf.parse("30/05/2020 19:32"), cli1, address2);

        Payment payment1 = new PaymentCard(null, PaymentStatus.SETTLED, req1, 6);
        req1.setPayment(payment1);

        Payment payment2 = new PaymentSlip(null, PaymentStatus.PENDING, req2, sdf.parse("31/05/2020 10:35"), null);
        req2.setPayment(payment2);

        cli1.getRequests().addAll(Arrays.asList(req1, req2));

        requestRepo.saveAll(Arrays.asList(req1, req2));
        paymentRepo.saveAll(Arrays.asList(payment1, payment2));

        RequestItem item1 = new RequestItem(req1, p1, 0.0, 1, 2000.00);
        RequestItem item2 = new RequestItem(req1, p3, 0.0, 2, 80.00);
        RequestItem item3 = new RequestItem(req2, p2, 100.0, 1, 800.00);

        req1.getRequestItems().addAll(Arrays.asList(item1, item2));
        req2.getRequestItems().addAll(Arrays.asList(item3));

        p1.getRequestItems().addAll(Arrays.asList(item1));
        p2.getRequestItems().addAll(Arrays.asList(item3));
        p3.getRequestItems().addAll(Arrays.asList(item2));

        requestItemRepo.saveAll(Arrays.asList(item1, item2, item3));
    }
}
