//package test.web_scraping.service;
//
//import lombok.Value;
//import org.springframework.stereotype.Service;
//import test.web_scraping.ScraperService;
//import test.web_scraping.model.ResponseDTO;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class ScraperServiceImpl implements ScraperService{
//
//    List<String> urls;
//    public Set<ResponseDTO> getVehicleByModel(String vehicleModel){
//        Set<ResponseDTO> responseDTOS = new HashSet<>();
//
//        for (String url: urls) {
//
//            if (url.contains("ikman")) {
//                extractDataFromIkman(responseDTOS, url + vehicleModel);
//            } else if (url.contains("riyasewana")) {
//                extractDataFromRiyasewana(responseDTOS, url + vehicleModel);
//            }
//
//        }
//    }
//}
