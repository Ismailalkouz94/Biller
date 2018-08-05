/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.utils;

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
public interface Utils {

    public static String parseTreeMenu(List<MenusTree> menueList) {

        String resutlt = "";

//        JSONObject data = new JSONObject();
        JSONArray arrayResult = new JSONArray();

        for (int i = 0; i < menueList.size(); i++) {

            MenusTree menusTree = menueList.get(i);

            if (menusTree.getType().equals("link")) {

                JSONObject jsonMenue = convertToJson(menusTree);

                arrayResult.put(jsonMenue);
            }

            if (menusTree.getType().equals("sub")) {

                List<MenusTree> childs = getAllChild(menusTree, menueList);

                JSONArray jsonArrayChild = new JSONArray();

                JSONObject jsonMenue = convertToJson(menusTree);

                for (MenusTree child : childs) {

                    JSONObject jsonChild = convertToJson(child);

                    List<MenusTree> childList = getAllChild(child, menueList);

                    if (childList.size() > 0) {

                        JSONArray jsonArrayChild2 = new JSONArray();

                        for (MenusTree chiled2 : childList) {

                            JSONObject jsonChild2 = convertToJson(chiled2);

                            jsonArrayChild2.put(jsonChild2);
                        }

                        jsonChild.put("children", jsonArrayChild2);
                    }

                    jsonArrayChild.put(jsonChild);
                }

                jsonMenue.put("children", jsonArrayChild);

                arrayResult.put(jsonMenue);
            }

        }

//        data.put("data", arrayResult);
        return arrayResult.toString();
    }

    public static String parseTree(List<MenusTree> menueList) {

        String resutlt = "";

        JSONObject data = new JSONObject();

        JSONArray arrayResult = new JSONArray();

        for (int i = 0; i < menueList.size(); i++) {

            MenusTree menusTree = menueList.get(i);

            if (menusTree.getType().equals("link")) {

                JSONObject jsonMenue = convertToJson(menusTree);

                arrayResult.put(jsonMenue);
            }

            if (menusTree.getType().equals("sub")) {

                List<MenusTree> childs = getAllChild(menusTree, menueList);

                JSONArray jsonArrayChild = new JSONArray();

                JSONObject jsonMenue = convertToJson(menusTree);

                for (MenusTree child : childs) {

                    JSONObject jsonChild = convertToJson(child);

                    List<MenusTree> childList = getAllChild(child, menueList);

                    if (childList.size() > 0) {

                        JSONArray jsonArrayChild2 = new JSONArray();

                        for (MenusTree chiled2 : childList) {

                            JSONObject jsonChild2 = convertToJson(chiled2);

                            jsonArrayChild2.put(jsonChild2);
                        }

                        jsonChild.put("children", jsonArrayChild2);
                    }

                    jsonArrayChild.put(jsonChild);
                }

                jsonMenue.put("children", jsonArrayChild);

                arrayResult.put(jsonMenue);
            }

        }

        data.put("data", arrayResult);

        return data.toString();
    }

    public static List<MenusTree> getAllChild(MenusTree tree, List<MenusTree> data) {

        List<MenusTree> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getParentMenusId() != null && Objects.equals(data.get(i).getParentMenusId(), tree.getMenusId())) {
                result.add(data.get(i));
            }
        }
        return result;
    }

    public static JSONObject convertToJson(MenusTree tree) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("menueId", tree.getMenusId());
        jsonObject.put("name", tree.getName());
        jsonObject.put("type", tree.getType());
        jsonObject.put("state", tree.getState());
        jsonObject.put("icon", tree.getIcon());

        return jsonObject;
    }

}
