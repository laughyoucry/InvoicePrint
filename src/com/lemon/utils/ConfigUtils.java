package com.lemon.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.lemon.vo.base.Company;
import com.lemon.vo.base.Goods;
import com.lemon.vo.template.Item;

/**
 * 配置文件类<br>
 * <p>
 * 功能： <br>
 * 1.读取配置文件到公共资源中 <br>
 * 2.保存公共资源到配置文件中 <br>
 */
public class ConfigUtils {

    /**
     * 资源文件的路径
     */
    public static final String RESOURCE = "/resource";

    /**
     * 基础数据存放的位置
     */
    public static final String DATA_PATH = "/data/base/";

    /**
     * 模版配置存放的位置
     */
    public static final String CONFIG = "/config/";

    /**
     * 购方公司信息
     */
    public static final Map<Integer, Company> buyCompanies = new HashMap<Integer, Company>();

    /**
     * 售方公司信息
     */
    public static final Map<Integer, Company> saleCompanies = new HashMap<Integer, Company>();

    /**
     * 商品信息
     */
    public static final Map<Integer, Goods> goodsMap = new HashMap<Integer, Goods>();

    /**
     * 模版元素信息
     */
    public static final Map<String, Item> itemsMap = new HashMap<String, Item>();

    /**
     * index信息
     */
    public static final Map<String, Integer> indexMap = new HashMap<String, Integer>();

    /** ["saleComIndex", "buyComIndex", "goodsIndex", "templateIndex"] */
    public static final String[] INDEX_NAME = { "saleComIndex", "buyComIndex", "goodsIndex", "templateIndex" };

    static {
        // 初始化配置
        init();
    }

    /**
     * 初始化配置
     */
    public static void init() {
        // 1、初始化购方公司信息
        String buyComFile = DATA_PATH + "buy_company.json";
        List<Company> buyCompanyList = getListFromFile(getFullPath(buyComFile), Company.class);
        if (buyCompanyList != null) {
            for (Company com : buyCompanyList) {
                buyCompanies.put(com.getId(), com);
            }
        }

        // 2、初始化售方公司信息
        String saleComFile = DATA_PATH + "sale_company.json";
        List<Company> saleCompanyList = getListFromFile(getFullPath(saleComFile), Company.class);
        if (saleCompanyList != null) {
            for (Company com : saleCompanyList) {
                saleCompanies.put(com.getId(), com);
            }
        }

        // 3、初始化商品信息
        String goodsFile = DATA_PATH + "goods.json";
        List<Goods> goodsList = getListFromFile(getFullPath(goodsFile), Goods.class);
        if (goodsList != null) {
            for (Goods goods : goodsList) {
                goodsMap.put(goods.getId(), goods);
            }
        }

        // 4、初始化模版信息
        String itemsFile = CONFIG + "module.json";
        List<Item> itemList = getListFromFile(getFullPath(itemsFile), Item.class);
        if (itemList != null) {
            for (Item item : itemList) {
                itemsMap.put(item.getId(), item);
            }
        }

        // 5、初始化index信息
        String indexFile = DATA_PATH + "index.json";
        Map<String, Integer> indexTmpMap = getObjectFromFile(getFullPath(indexFile), Map.class);
        if (indexTmpMap != null) {
            for (String key : indexTmpMap.keySet()) {
                indexMap.put(key, indexTmpMap.get(key));
            }
        }
    }

    /**
     * 保存设置
     */
    public static void save() {
        // 1、保存购方公司信息
        String buyComFile = DATA_PATH + "buy_company.json";
        if (buyCompanies != null) {
            saveObjectToFile(buyComFile, buyCompanies.values());
        }

        // 2、保存售方公司信息
        String saleComFile = DATA_PATH + "sale_company.json";
        if (saleCompanies != null) {
            saveObjectToFile(saleComFile, saleCompanies.values());
        }

        // 3、保存商品信息
        String goodsFile = DATA_PATH + "goods.json";
        if (goodsMap != null) {
            saveObjectToFile(goodsFile, goodsMap.values());
        }

        // 4、初始化模版信息
//        String itemsFile = CONFIG + "module.json";
//        if (itemsMap != null) {
//            saveObjectToFile(itemsFile, itemsMap.values());
//        }

        // 5、初始化index信息
        String indexFile = DATA_PATH + "index.json";
        if (indexMap != null) {
            saveObjectToFile(indexFile, indexMap);
        }
    }

    /**
     * 获取序列
     * 
     * @param indexName
     * @return
     */
    public static int getIndex(String indexName) {
        int index = 1;
        if (indexMap.get(indexName) != null) {
            index = indexMap.get(indexName);
        }
        indexMap.put(indexName, index + 1);
        return index;
    }

    /**
     * 获取全文件路径
     *
     * @param fileName
     * @return
     */
    public static String getFullPath(String fileName) {
        return FileUtils.getRootPath() + RESOURCE + fileName;
    }

    /**
     * 从文件中获取对象
     *
     * @param fullPath
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObjectFromFile(String fullPath, Class<T> clazz) {
        try {
            String jsonStr = new String(FileUtils.readFromFile(fullPath), "utf-8");
            return JSON.parseObject(jsonStr, clazz);
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的文件编码");
        }
        return null;
    }

    /**
     * 从文件中获取list对象
     *
     * @param fullPath
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getListFromFile(String fullPath, Class<T> clazz) {
        String jsonStr = null;
        try {
            jsonStr = new String(FileUtils.readFromFile(fullPath), "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的文件编码");
        }
        return JSON.parseArray(jsonStr, clazz);
    }

    /**
     * 保存对象到文件中
     *
     * @param fileName
     * @param content
     */
    public static void saveFile(String fileName, String content) {
        String fullPath = getFullPath(fileName);
        FileUtils.writeFile(fullPath, content, "utf-8");
    }

    /**
     * 保存对象到文件中
     *
     * @param fileName
     * @param obj
     */
    public static void saveObjectToFile(String fileName, Object obj) {
        String content = JSON.toJSONString(obj);
        saveFile(fileName, content);
    }

    /**
     * 获取公司名称
     * 
     * @return
     */
    public static Vector<String> getCompanyItems(Map<Integer, Company> companies) {
        Vector<String> items = new Vector<String>();
        if (!companies.isEmpty()) {
            for (Integer key : companies.keySet()) {
                items.add(key + ":" + companies.get(key).getName());
            }
        }
        return items;
    }

    /**
     * 获取商品名称
     *
     * @return
     */
    public static Vector<String> getGoodsItems(){
        Vector<String> items = new Vector<String>();
        if (!goodsMap.isEmpty()) {
            for (Integer key : goodsMap.keySet()) {
                items.add(key + ":" + goodsMap.get(key).getName());
            }
        }
        return items;
    }

    public static void main(String[] args) {
        List<Company> buyCompanies = new ArrayList<Company>();
        for (int i = 0; i < 100; i++) {
            Company company = new Company(i, "四川金苗农业科技有限公司", "12312312", "丝丝的减肥而过 031-23423424", "中国银行 87345235345345");
            buyCompanies.add(company);
        }
        ConfigUtils.saveObjectToFile(DATA_PATH + "buy_company.json", buyCompanies);

        // System.out.println(buyCompanies.size());
        // System.out.println(buyCompanies.get(88).getBankNameAndNo());
        //
        // System.out.println(saleCompanies.size());
        // System.out.println(goodsMap.size());
        // System.out.println(itemsMap.size());
    }

    /**
     * 根据选中的项获得公司对象
     * @param item
     * @param buyCompanies
     * @return
     */
    public static Company getCompanyByItem(String item, Map<Integer, Company> buyCompanies) {
        if(item != null && item.indexOf(":") != -1){
            String key = item.split(":")[0];
            return buyCompanies.get(Integer.parseInt(key));
        }
        return null;
    }
}
