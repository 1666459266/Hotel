package com.hotel.core.mq;

import com.hotel.core.entity.*;
import com.hotel.core.mapper.UserProductMapper;
import com.hotel.core.mapper.UserRestaurantMapper;
import com.hotel.core.mapper.UserRoomMapper;
import com.hotel.core.service.*;
import com.hotel.core.utils.BaseController;
import com.hotel.core.utils.TypeChange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQRecv {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private UserProductMapper userProductMapper;
    @Autowired
    private UserRestaurantService userRestaurantService;
    @Autowired
    private UserRestaurantMapper userRestaurantMapper;
    @Autowired
    private UserRoomService userRoomService;
    @Autowired
    private UserRoomMapper userRoomMapper;

    @RabbitListener(queues = MQConfig.PRODUCT_QUEUE_NAME)
    public void recvProduct(String message){
        UserProduct userProduct = TypeChange.stringToBean(message, UserProduct.class);
        //根据传入的参数查询用户和商品
        Users users = usersService.selectUsersById(userProduct.getUserId());
        Product product = productService.selectProductById(userProduct.getProductId());
        if (users == null || product == null) {
            System.out.println("信息为空");
            return;
        }
        //减去用户购买的商品数量
        product.setProductNum(product.getProductNum()-userProduct.getProductNum());
        //下单后提升商品热度
        product.setProductPopularity(product.getProductPopularity()+1);
        int i = productService.updateProduct(product, BaseController.KEYLIST);
        //存入用户商品订单
        userProduct.setUserId(users.getId());
        userProduct.setProductId(product.getId());
        userProduct.setUsername(users.getUsername());
        userProduct.setProductName(product.getProductName());
        userProduct.setProductPrice(product.getProductUnitPrice());
        int j = userProductMapper.insertSelective(userProduct);
        //清除缓存
        userProductService.clearCache(BaseController.KEYLIST);
        if (j > 0) {
            System.out.println("添加订单成功");
        } else {
            System.out.println("添加订单失败");
        }
    }

    @RabbitListener(queues = MQConfig.RESTAURANT_QUEUE_NAME)
    public void recvRestaurant(String message){
        UserRestaurant userRestaurant = TypeChange.stringToBean(message, UserRestaurant.class);
        //根据传入的用户userId和restaurantId查询用户和用餐
        Users users = usersService.selectUsersById(userRestaurant.getUserId());
        Restaurant restaurant = restaurantService.selectRestaurantById(userRestaurant.getRestaurantId());
        if (users == null || restaurant == null) {
            System.out.println("信息为空");
            return;
        }
        //减去用户购买的商品数量
        restaurant.setFoodNum(restaurant.getFoodNum()-userRestaurant.getFoodNum());
        restaurant.setFoodPopularity(restaurant.getFoodPopularity()+1);
        int i = restaurantService.updateRestaurant(restaurant,BaseController.KEYLIST);
        //存入用户商品订单
        userRestaurant.setUserId(users.getId());
        userRestaurant.setRestaurantId(restaurant.getId());
        userRestaurant.setUsername(users.getUsername());
        userRestaurant.setFoodName(restaurant.getFoodName());
        userRestaurant.setFoodUnitPrice(restaurant.getFoodUnitPrice());
        int j = userRestaurantMapper.insertSelective(userRestaurant);
        //清除缓存
        userRestaurantService.clearCache(BaseController.KEYLIST);
        if (j > 0) {
            System.out.println("添加订单成功");
        } else {
            System.out.println("添加订单失败");
        }
    }

    @RabbitListener(queues = MQConfig.ROOM_QUEUE_NAME)
    public void recvRoom(String message){
        UserRoom userRoom = TypeChange.stringToBean(message, UserRoom.class);
        //根据传入的userId和roomId查询用户和房间
        Users users = usersService.selectUsersById(userRoom.getUserId());
        Room room = roomService.selectRoomById(userRoom.getRoomId());
        if (users == null || room == null) {
            System.out.println("信息为空");
            return;
        }
        //将房间的状态修改为已预定
        room.setRoomStates(3);
        int i = roomService.updateRoom(room, BaseController.KEYLIST);
        //存入用户房间订单
        userRoom.setUserId(users.getId());
        userRoom.setRoomId(room.getId());
        userRoom.setUsername(users.getUsername());
        userRoom.setRoomNumber(room.getRoomNumber());
        userRoom.setRoomType(room.getRoomType());
        userRoom.setRoomPrice(room.getRoomPrice());
        int j = userRoomMapper.insertSelective(userRoom);
        //清除缓存
        userRoomService.clearCache(BaseController.KEYLIST);
        if (j > 0) {
            System.out.println("添加订单成功");
        } else {
            System.out.println("添加订单失败");
        }
    }

}
