package com.example.sona.Model;

import com.example.sona.Entity.Menu;
import lombok.Data;
import java.util.List;

@Data
public class MenuAddResponse {
    private String menuId;
    private String menuItemName;
    private String price;
    private Integer quantity;
    private MenuDTO menuDTO;

    private List<Menu>menuList;
}
