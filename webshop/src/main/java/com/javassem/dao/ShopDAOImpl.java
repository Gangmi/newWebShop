package com.javassem.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.ProductVO;

@Repository("shopDAO")
public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public int getCatTotal(ProductVO vo) {

		// 해당하는 카테고리의 전체 갯수를 가져오기
		int catTot = mybatis.selectOne("ShopDAO.getCatTotal", vo);

		// 가져온 페이지당 갯수를 int로 변환
		int itemquan = Integer.parseInt(vo.getItemQuan());

		// 페이지갯수를 구하기
		int totalpage = 0;

		if (catTot % itemquan > 0) { // 전체 갯수를 페이지당 갯수로 나눈 나머지가 1보다 크면,

			// 전체 페이지는 전체 갯수 나누기 페이지당 갯수 +1로한다.
			return totalpage = (catTot / itemquan) + 1;
		} else {
			// 그게 아니라면 전체갯수에서 페이지당 아이템을 나눈 값으로 한다.
			return totalpage = catTot / itemquan;
		}
	}

	// 해당 카테고리의 상품을 가져오기
	public List<ProductVO> getProductDetail(ProductVO vo) {

		getCatTotal(vo);

		// 가져온 페이지당 갯수를 int로 변환
		int itemquan = Integer.parseInt(vo.getItemQuan());

		// 페이지갯수를 구하기
		int totalpage = 0;

		// 해쉬맵으로 조회에 필요한 값을 넘김
		HashMap map = new HashMap();
		int page = Integer.parseInt(vo.getPage());
		int end = page * itemquan;
		int start = end - (itemquan - 1);

		map.put("cat", vo.getP_cat());
		map.put("start", start);
		map.put("end", end);

		// vo 에 저장된 카테고리에서 , 해당하는 페이지의 해당하는 물품갯수만큼 가져와서 리스트에 저장한다.
		List<ProductVO> result = mybatis.selectList("ShopDAO.getProductDetail", map);

		return result;
	}

	@Override
	public ProductVO getOneProduct(ProductVO vo) {
		ProductVO result = mybatis.selectOne("ShopDAO.getOneProduct", vo);

		return result;
	}

	/*
	 * public void updateShop(ShopVO vo) {
	 * System.out.println("===> Mybatis updateShop() 호출");
	 * mybatis.update("ShopDAO.updateshop", vo); }
	 * 
	 * public void deleteShop(ShopVO vo) {
	 * System.out.println("===> Mybatis deleteShop() 호출");
	 * mybatis.delete("ShopDAO.deleteshop", vo); }
	 * 
	 * public ShopVO getShop(ShopVO vo) {
	 * System.out.println("===> Mybatis getShop() 호출"); return
	 * mybatis.selectOne("ShopDAO.getshop", vo); }
	 * 
	 * public List<ShopVO> getShopList(ShopVO vo) {
	 * System.out.println("===> Mybatis getShopList() 호출"); return
	 * mybatis.selectList("ShopDAO.getshopList", vo); }
	 */
}