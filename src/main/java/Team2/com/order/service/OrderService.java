package Team2.com.order.service;

import Team2.com.member.entity.Member;
import Team2.com.order.dto.OrderRequestItemDto;
import Team2.com.order.dto.OrderResponseDto;
import Team2.com.order.dto.OrderResultDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {
    void order(List<OrderRequestItemDto> items, Member member); // 주문하기
    OrderResultDto getOrders(int offset, int limit); // (고객) 주문내역 전체 조회
    List<OrderResponseDto> getAllCustomerBuyList(PageRequest pageRequest, String sellerName); //(판매자) 주문내역 전체 조회
    OrderResponseDto getCustomerBuyItem(Long orderId, String sellerName); //(판매자)주문 내역 조회
    void orderCompleteProceeding(Long orderId, String sellerName); //(판매자)주문완료처리
}
