package com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.feign;



import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.MaBanKsu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACHATKSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface MaBanKsuRestClient {

    @GetMapping("/api/MaBanKsu/typeMaBanKsu/{id}")
    public MaBanKsu getMaBanKsu(@PathVariable Long id);

    @GetMapping("/api/MaBanKsu/get/{id}")
    public MaBanKsu mabanKsu(@PathVariable("id") Long id);

    @GetMapping("/api/MaBanKsu/curentMaBanKsuId")
    public MaBanKsu getCurrentMaBanKsuById();
}
