package com.ls.adsearch.index.keyword;

import com.ls.adsearch.index.IndexAware;
import com.ls.adsearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author lijiayin
 */
@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {
    
    private static Map<String, Set<Long>> keywordUnitMap;
    private static Map<Long, Set<String>> unitKeywordMap;
    
    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }
    
    
    @Override
    public Set<Long> get(String key) {
        
        if(StringUtils.isBlank(key)){
            return Collections.emptySet();
        }
        
        Set<Long> result = keywordUnitMap.get(key);
        if(CollectionUtils.isEmpty(result)){
            return Collections.emptySet();
        }
        
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitKeywordIndex before add: {}", unitKeywordMap);
        
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key, 
                keywordUnitMap,
                ConcurrentSkipListSet::new);
        unitIdSet.addAll(value);
        
        for (Long unitId : value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId, 
                    unitKeywordMap,
                    ConcurrentSkipListSet::new);
            keywordSet.add(key);
        }

        log.info("UnitKeywordIndex after add: {}", unitKeywordMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex before delete: {}", unitKeywordMap);
        
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key, 
                keywordUnitMap, 
                ConcurrentSkipListSet::new);
        unitIdSet.removeAll(value);
        
        for (Long unitId : value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId, 
                    unitKeywordMap, 
                    ConcurrentSkipListSet::new);
            keywordSet.remove(key);
        }

        log.info("UnitKeywordIndex after delete: {}", unitKeywordMap);
    }
    
    public boolean match(Long unitId, List<String> keywords) {
        if(unitKeywordMap.containsKey(unitId) 
                && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){
            
            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            
            return CollectionUtils.isSubCollection(keywords, unitKeywords);
        }
        return false;
    }
}
