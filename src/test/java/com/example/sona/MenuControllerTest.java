package com.example.sona;

import com.example.sona.Controller.MenuController;
import com.example.sona.Model.MenuAddResponse;
import com.example.sona.Model.MenuDTO;
import com.example.sona.Repository.MenuRepository;
import com.example.sona.Service.MenuService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MenuControllerTest {
    @InjectMocks
    private MenuController menuController;

    @Mock
    private MenuService menuService;
    @Mock
    MenuRepository menuRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Helper methods

    private MenuAddResponse menuAddResponse() {
        MenuAddResponse menuAddResponse = new MenuAddResponse();
        menuAddResponse.setMenuId("123");
        menuAddResponse.setMenuItemName("Momo");
        menuAddResponse.setPrice("15.99");
        menuAddResponse.setQuantity(20);
        return menuAddResponse;
    }

    private MenuDTO menuRequest() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuItemName("Pakora");
        menuDTO.setQuantity(10);
        return menuDTO;
    }

    @Test
    public void addMenuItemTest() {
        MenuAddResponse menuAddResponse = menuAddResponse();
        MenuDTO expectedMenuDTO = menuRequest();

        Mockito.when(menuService.addMenuItems(Mockito.any())).thenReturn(expectedMenuDTO);

        ResponseEntity<?> responseEntity = menuController.addMenuItem(menuAddResponse);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(expectedMenuDTO, responseEntity.getBody());
    }

    @Test
    public void addManyItemsTest() {
        List<MenuAddResponse> menuAddResponseList = new ArrayList<>();
        menuAddResponseList.add(menuAddResponse());

        List<MenuDTO> expectedMenuDTOList = new ArrayList<>();
        expectedMenuDTOList.add(menuRequest());

        Mockito.when(menuService.addMultipleItem(Mockito.any())).thenReturn(expectedMenuDTOList);

        ResponseEntity<?> responseEntity = menuController.addManyItems(menuAddResponseList);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<MenuDTO> actualMenuDTOList = (List<MenuDTO>) responseEntity.getBody();
        assertIterableEquals(expectedMenuDTOList, actualMenuDTOList);


    }

}
