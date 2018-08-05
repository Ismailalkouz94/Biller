/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.utils;

import com.mycompany.biller.resources.CategoryTree;
import com.mycompany.biller.resources.MenusTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public interface Utils1 {

    public static String parseTreeDropDown(List<CategoryTree> menueList) {

        String resutlt = "";

        JSONObject data = new JSONObject();
        JSONArray arrayResult = new JSONArray();

        int c = 0;
        for (int i = 0; i < menueList.size(); i++) {

            CategoryTree menusTree = menueList.get(i);

            if (!hasChiled(menusTree, menueList) && menusTree.getParentCategoryId() == null) {

                JSONObject jsonMenue = convertToJsonDropDown(menusTree, true);

                arrayResult.put(jsonMenue);

            } else {
                List<CategoryTree> childs = getAllChild(menusTree, menueList);

                if (childs.size() != 0) {
//                    c++;
                    JSONArray jsonArrayChild = new JSONArray();

                    JSONObject jsonMenue = convertToJsonDropDown(menusTree, true);

                    for (CategoryTree child : childs) {
//                        c++;
                        JSONObject jsonChild = null;
                        List<CategoryTree> childList = getAllChild(child, menueList);

                        if (childList.size() > 0) {
                            jsonChild = convertToJsonDropDown(child, true);

                            JSONArray jsonArrayChild2 = new JSONArray();

                            for (CategoryTree chiled2 : childList) {
//                                c++;

                                JSONObject jsonChild2 = convertToJsonDropDown(chiled2, false);

                                jsonArrayChild2.put(jsonChild2);

                            }

                            jsonChild.put("children", jsonArrayChild2);
                        } else {
                            jsonChild = convertToJsonDropDown(child, false);
                        }

                        jsonArrayChild.put(jsonChild);

                    }

                    jsonMenue.put("children", jsonArrayChild);

                    arrayResult.put(jsonMenue);

                }
                i = i + c;
            }

        }

        data.put("data", arrayResult);
//        return arrayResult.toString();
        return data.toString();
    }

    public static String parseTree(List<CategoryTree> menueList) {

        String resutlt = "";

        JSONObject data = new JSONObject();
        JSONArray arrayResult = new JSONArray();

        int c = 0;
        for (int i = 0; i < menueList.size(); i++) {

            CategoryTree menusTree = menueList.get(i);

            if (!hasChiled(menusTree, menueList) && menusTree.getParentCategoryId() == null) {

                JSONObject jsonMenue = convertToJson(menusTree);

                arrayResult.put(jsonMenue);

            } else {
                List<CategoryTree> childs = getAllChild(menusTree, menueList);

                if (childs.size() != 0) {
//                    c++;
                    JSONArray jsonArrayChild = new JSONArray();

                    JSONObject jsonMenue = convertToJson(menusTree);

                    for (CategoryTree child : childs) {
//                        c++;
                        JSONObject jsonChild = convertToJsonChild(child);

                        List<CategoryTree> childList = getAllChild(child, menueList);

                        if (childList.size() > 0) {

                            JSONArray jsonArrayChild2 = new JSONArray();

                            for (CategoryTree chiled2 : childList) {
//                                c++;

                                JSONObject jsonChild2 = convertToJsonChild(chiled2);

                                jsonArrayChild2.put(jsonChild2);

                            }

                            jsonChild.put("children", jsonArrayChild2);
                        }

                        jsonArrayChild.put(jsonChild);

                    }

                    jsonMenue.put("children", jsonArrayChild);

                    arrayResult.put(jsonMenue);

                }
                i = i + c;
            }

        }

        data.put("data", arrayResult);
//        return arrayResult.toString();
        return data.toString();
    }

    public static List<CategoryTree> getAllChild(CategoryTree tree, List<CategoryTree> data) {

        List<CategoryTree> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getParentCategoryId() != null && Objects.equals(data.get(i).getParentCategoryId(), tree.getCategoryId())) {
                result.add(data.get(i));
            }
        }
        return result;
    }

    public static boolean hasChiled(CategoryTree tree, List<CategoryTree> data) {

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getParentCategoryId() != null && Objects.equals(data.get(i).getParentCategoryId(), tree.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public static JSONObject convertToJson(CategoryTree tree) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("value", tree.getCategoryId());
        jsonObject.put("label", tree.getDescription());
        jsonObject.put("data", tree.getDescription());
        jsonObject.put("expandedIcon", "fa-folder-open");
        jsonObject.put("collapsedIcon", "fa-folder");

        return jsonObject;
    }

    public static JSONObject convertToJsonChild(CategoryTree tree) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("value", tree.getCategoryId());
        jsonObject.put("label", tree.getDescription());
        jsonObject.put("data", tree.getDescription());
        jsonObject.put("icon", "fa-file-image-o");

        return jsonObject;
    }

    public static JSONObject convertToJsonDropDown(CategoryTree tree, boolean varBol) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("value", tree.getCategoryId());
        jsonObject.put("text", tree.getDescription());
        jsonObject.put("collapsed", varBol); //has chiled

        return jsonObject;
    }

}
