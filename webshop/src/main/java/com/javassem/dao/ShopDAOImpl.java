package com.javassem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javassem.domain.ProductVO;

@Repository("shopDAO")
public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public int getCatTotal(ProductVO vo) {
		// 전체 아이템수
		int catTot = 0;

		// 해쉬맵으로 카테고리 설정
		HashMap map = new HashMap();

		map.put("p_cat", vo.getP_cat());

		// 해당하는 카테고리의 전체 갯수를 가져오기(브랜드가 없을 때)
//		if (vo.getP_brand() == null) {
//
//			catTot = mybatis.selectOne("ShopDAO.getCatTotal", map);
//		} else 
		if (vo.getP_brand() != null) {
			// 브랜드가 있을 때
			// string tokenizer로 /를 기준으로 자르기
			StringTokenizer sc = new StringTokenizer(vo.getP_brand(),"/");

			List<String> brandlist = new ArrayList();
			int i = 0;
			while (sc.hasMoreTokens()) {
				brandlist.add(i, sc.nextToken());
				i++;
			}
			// 맵에 브랜드 리스트 추가
			map.put("brand", brandlist);

		}
		//색에 대한 검색이 들어오면
		if(vo.getP_color()!=null) {
			//맵퍼에 색을 지정
			map.put("selectcolor", vo.getP_color());
		}

		// 전체 갯수를 구하기 위해 맵퍼로 전송
		catTot = mybatis.selectOne("ShopDAO.getCatTotal", map);

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

		// getCatTotal(vo);

		HashMap map = new HashMap();
		// 브랜드 리스트가 있으면
		List<String> brandlist = new ArrayList();
		if (vo.getP_brand() != null) {

			StringTokenizer sc = new StringTokenizer(vo.getP_brand(), "/");

			int i = 0;
			while (sc.hasMoreTokens()) {

				brandlist.add(i, sc.nextToken());

				i++;

			}
			map.put("brand", brandlist);

		}
		// 맵에 브랜드 리스트 추가

		// 컬러에대한 검색이 있다면,
		if (vo.getP_color() != null) {
			map.put("selectcolor", vo.getP_color());
		}

		// 가져온 페이지당 갯수를 int로 변환
		int itemquan = Integer.parseInt(vo.getItemQuan());

		// 해쉬맵으로 조회에 필요한 값을 넘김

		// 페이지값을 가져옴
		int page = Integer.parseInt(vo.getPage());

		// 전체 있어야할 페이지값을 가져옴
		int totpage = getCatTotal(vo);

		// 만약 현재 페이지가 나올 페이지의 값보다 크면,
		if (page > totpage) {
			// 페이지를 1로 지정함
			page = 1;

			// 의자 카테고르에서 페이지당 4개로 설정된 2페이지에서 상품을 보고있다고 가정했을 때 이케아를 눌렀는데
			// 이케아의 해당 카테고리 품목이 2개 밖에 없다면 2페이지가 나오지 못해서 에러가 남
		}

		int end = page * itemquan;
		int start = end - (itemquan - 1);

		System.out.println(vo.getP_color());

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

}