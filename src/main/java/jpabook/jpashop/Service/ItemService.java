package jpabook.jpashop.Service;


import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 클래스 전체 메서드 읽기 전용으로 선언
@RequiredArgsConstructor
public class ItemService {

    private  final ItemRepository itemRepository;

    @Transactional  // 이렇게 메서드에 트랜잭션을 걸면 클래스의 트랜잭션보다 우선권을 가지게된다.
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems () {
        return itemRepository.findAll();
    }

    public Item findOne (Long itemId){
        return itemRepository.findOne(itemId);
    }


}
