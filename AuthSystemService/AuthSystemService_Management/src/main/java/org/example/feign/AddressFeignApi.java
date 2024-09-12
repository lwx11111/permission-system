package org.example.feign;

import org.example.feign.fallback.AddressFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "address",fallback = AddressFeignFallback.class)
public interface AddressFeignApi {


}
