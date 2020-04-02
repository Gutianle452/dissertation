package com.ye.modules.cus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericItemPreferenceArray;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ye.modules.cus.dao.LikesDao;
import com.ye.modules.cus.dao.MenuDao;
import com.ye.modules.cus.dao.UserDao;
import com.ye.modules.cus.entity.LikesEntity;
import com.ye.modules.cus.entity.MenuEntity;
import com.ye.modules.cus.entity.UserEntity;
import com.ye.modules.cus.service.MenuService;



@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private LikesDao likeDao;
	@Override
	public MenuEntity queryObject(Integer id){
		return menuDao.queryObject(id);
	}
	
	@Override
	public List<MenuEntity> queryList(Map<String, Object> map){
		return menuDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return menuDao.queryTotal(map);
	}
	
	@Override
	public void save(MenuEntity menu){
		menuDao.save(menu);
	}
	
	@Override
	public void update(MenuEntity menu){
		menuDao.update(menu);
	}
	
	@Override
	public void delete(Integer id){
		menuDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		menuDao.deleteBatch(ids);
	}
	@Override
	public List<MenuEntity> recommendList(Integer userId){
		
		Map<String, Object> map=new HashMap<String, Object>();
		  List<MenuEntity> goods=new ArrayList<MenuEntity>();
		 UserEntity user=userDao.queryObject(userId);
		try{
		 	map.clear();
		      map.put("sidx", "rand()");
		      
		      goods=	 menuDao.queryList(map);
			map.put("userId", userId);
		List<LikesEntity> list=likeDao.queryList(map); 
		int length=list.size();
		FastByIDMap<PreferenceArray> itemData=new FastByIDMap<PreferenceArray>();
		for (int i = 0; i < length; i++) {
			GenericItemPreferenceArray array=new GenericItemPreferenceArray(length);
			array.setItemID(i, list.get(i).getMenuId());
//			array.setUserID(i, user);
			array.setValue(i,   RandomUtils.nextFloat());
			
			itemData.put(i, array);
		}
		 
	 
		DataModel dataItemModel=new GenericDataModel(itemData);
		
		 
      ItemSimilarity itemsimilarity = new PearsonCorrelationSimilarity(dataItemModel);//计算内容相似度  
      GenericItemBasedRecommender recommender1 = new GenericItemBasedRecommender(dataItemModel, itemsimilarity);//构造推荐引擎 
       
      // 给用户推荐物品（这里是给用户4推荐5个物品）
      List<RecommendedItem> recommendItems = recommender1.recommendedBecause(userId, list.get(RandomUtils.nextInt(  length)).getMenuId().intValue(),  10);
 
      // 打印结果
      for (RecommendedItem recommendedItem : recommendItems) {
          System.out.println(recommendedItem);
          goods.add(menuDao.queryObject(recommendedItem.getItemID()));
      }
		}catch(Exception e){
			 
		
		}
		return goods;
	}
}
