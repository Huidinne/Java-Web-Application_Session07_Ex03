package org.example.ex_03.service;

import org.example.ex_03.model.Food;
import org.example.ex_03.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public String addFood(String name, double price, String category, MultipartFile file) {
        if (file.isEmpty()) {
            return "Chưa chọn ảnh";
        }

        String filename = file.getOriginalFilename();
        if (filename == null ||
                !(filename.endsWith(".jpg") ||
                        filename.endsWith(".png") ||
                        filename.endsWith(".jpeg"))) {
            return "Sai định dạng ảnh";
        }

        if (price < 0) {
            return "Giá không hợp lệ";
        }

        String uploadDir = "C:/RikkeiFood_Temp/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadDir + filename;

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi lưu file";
        }

        Food food = new Food(name, price, category, filePath);
        foodRepository.save(food);

        System.out.println("Đã thêm: " + name);
        System.out.println("Tổng món: " + foodRepository.count());

        return "OK";
    }
}

