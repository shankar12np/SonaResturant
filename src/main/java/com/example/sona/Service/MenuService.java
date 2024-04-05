package com.example.sona.Service;

import com.example.sona.Entity.Menu;
import com.example.sona.Model.MenuAddResponse;
import com.example.sona.Model.MenuDTO;
import com.example.sona.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public MenuDTO addMenuItems(MenuAddResponse menuAddResponse) {
       Menu menu = new Menu();
        menu.setMenuId(menuAddResponse.getMenuId());
        menu.setMenuItemName(menuAddResponse.getMenuItemName());
        menu.setPrice(menuAddResponse.getPrice());
        menu.setQuantity(menuAddResponse.getQuantity());

        menuRepository.save(menu);

        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuId(menu.getMenuId());
        menuDTO.setMenuItemName(menu.getMenuItemName());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setQuantity(menu.getQuantity());
        return menuDTO;

    }

    public List<MenuDTO> addMultipleItem(List<MenuAddResponse> menuAddResponseList) {
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (MenuAddResponse menuAddResponse : menuAddResponseList) {
            Menu menu = new Menu();
            menu.setMenuId(menuAddResponse.getMenuId());
            menu.setMenuItemName(menuAddResponse.getMenuItemName());
            menu.setPrice(menuAddResponse.getPrice());
            menu.setQuantity(menuAddResponse.getQuantity());

            menuRepository.save(menu);

            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuId(menu.getMenuId());
            menuDTO.setMenuItemName(menu.getMenuItemName());
            menuDTO.setPrice(menu.getPrice());
            menuDTO.setQuantity(menu.getQuantity());

            menuDTOList.add(menuDTO);
        }
        return menuDTOList;
    }



}
