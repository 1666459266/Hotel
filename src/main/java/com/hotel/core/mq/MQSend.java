package com.hotel.core.mq;

import com.hotel.core.entity.*;
import com.hotel.core.utils.TypeChange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendProduct(UserProduct userProduct){
        String msg = TypeChange.beanToString(userProduct);
        System.out.println("sendProduct msg =" + msg);
        rabbitTemplate.convertAndSend(MQConfig.PRODUCT_QUEUE_NAME,msg);
    }

    public void sendRestaurant(UserRestaurant userRestaurant){
        String msg = TypeChange.beanToString(userRestaurant);
        System.out.println("sendRestaurant msg =" + msg);
        rabbitTemplate.convertAndSend(MQConfig.RESTAURANT_QUEUE_NAME,msg);
    }

    public void sendRoom(UserRoom userRoom){
        String msg = TypeChange.beanToString(userRoom);
        System.out.println("sendRoom msg =" + msg);
        rabbitTemplate.convertAndSend(MQConfig.ROOM_QUEUE_NAME,msg);
    }

}
