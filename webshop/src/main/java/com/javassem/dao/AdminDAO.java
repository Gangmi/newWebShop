
package com.javassem.dao;

import java.util.List;

import com.javassem.domain.DeliveryVO;
import com.javassem.domain.MemberVO;
import com.javassem.domain.ProductVO;


public interface AdminDAO {

	public List<ProductVO> selectProduct(ProductVO vo);
	public List<MemberVO> selectMember(MemberVO vo);
	public List<DeliveryVO> selectDelivery(DeliveryVO vo);
	public List<ProductVO> inventoryInsert(ProductVO vo);
	public List<ProductVO> inventoryUpdate(ProductVO vo);
	public int memberDelete(MemberVO vo);
	public int[] salesMonth();
	public int[] salesCategory();
	public int[] recentSales();
	public int orderCount();
}
