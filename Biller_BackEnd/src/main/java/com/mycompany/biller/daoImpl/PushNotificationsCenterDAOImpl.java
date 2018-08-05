/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biller.daoImpl;

import com.mycompany.biller.dao.PushNotificationsCenterDAO;
import com.mycompany.biller.resources.ToBiller;
import com.mycompany.biller.resources.ToPerson;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abu3jram
 */
@Repository
public class PushNotificationsCenterDAOImpl implements PushNotificationsCenterDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ToBiller> findSubscribers(ToBiller toBiller) {
        Long partyId = Long.valueOf((String) toBiller.getPartyId());
        String commericalRegistrationNum = toBiller.getCommericalRegistrationNum();
        String partyGroupName = toBiller.getPartyGroupName();
        String companyId = toBiller.getCompanyId();
        String partySizeId = "";

        if (commericalRegistrationNum == null || commericalRegistrationNum.isEmpty()) {
            commericalRegistrationNum = "";
        }
        if (partyGroupName == null || partyGroupName.isEmpty()) {
            partyGroupName = "";
        }
        System.out.println("partySizeId     **" + toBiller.getPartySizeId());
        if (toBiller.getPartySizeId() != null) {
            if (!toBiller.getPartySizeId().equals("")) {
                partySizeId = String.valueOf(toBiller.getPartySizeId());
            }
        }

        List companyTypeList = toBiller.getCompanyTypeList();
        List cityIdList = toBiller.getCityIdList();
        System.out.println("companyTypeList     **" + companyTypeList);

        List<ToBiller> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("Select V.PARTY_ID partyId,V.PARTY_FAV partyFav,G.TRADE_NAME trandName,\n"
                        + "G.COMPANY_TYPE companyType,CT.DESCRIPTION companyTypeDesc,G.COMMERICAL_REGISTRATION_NUM commericalRegistrationNum,\n"
                        + "G.PARTY_GROUP_NAME partyGroupName,G.PARTY_SIZE_ID partySizeId,PS.DESCRIPTION partySizeDesc,P.CITY_ID cityId,\n"
                        + "C.DESCRIPTION cityDesc,CN.DESCRIPTION countryDesc,G.COMPANY_ID companyId,UL.USER_LOGIN_ID userLoginId\n"
                        + "from PARTY_GROUP G\n"
                        + "Inner Join PARTY_GROUP_FAV V on G.PARTY_ID = V.PARTY_FAV\n"
                        + "Inner Join PARTY P on G.PARTY_ID = P.PARTY_ID\n"
                        + "Inner Join COMPANY_TYPE CT on G.COMPANY_TYPE = CT.ID\n"
                        + "Inner Join PARTY_SIZE PS on G.PARTY_SIZE_ID = PS.PARTY_SIZE_ID\n"
                        + "Inner Join CITY C on P.CITY_ID = C.CITY_ID\n"
                        + "Inner Join COUNTRY CN on C.COUNTRY_ID = CN.COUNRTY_ID\n"
                        + "Inner Join USER_LOGIN UL on V.PARTY_FAV =UL.PARTY_ID\n"
                        + "Where V.PARTY_ID = :partyId\n"//25
                        + "And G.COMPANY_TYPE in (:companyTypeList)\n"
                        + "And P.CITY_ID in (:cityIdList)\n"
                        + "And UPPER(G.PARTY_GROUP_NAME) like UPPER('%' || :partyGroupName || '%')\n"
                        + "And G.COMMERICAL_REGISTRATION_NUM like '%' || :commericalRegistrationNum || '%'\n"
                        + "And G.COMPANY_ID like '%' || :companyId || '%'\n"
                        + "And G.PARTY_SIZE_ID = nvl(:partySizeId,G.PARTY_SIZE_ID)")
                .addScalar("partyId")
                .addScalar("partyFav")
                .addScalar("trandName")
                .addScalar("companyType")
                .addScalar("companyTypeDesc")
                .addScalar("commericalRegistrationNum")
                .addScalar("partyGroupName")
                .addScalar("partySizeId")
                .addScalar("partySizeDesc")
                .addScalar("cityId")
                .addScalar("cityDesc")
                .addScalar("countryDesc")
                .addScalar("companyId")
                .addScalar("userLoginId")
                .setResultTransformer(Transformers.aliasToBean(ToBiller.class))
                .setParameter("partyId", partyId)
                .setParameterList("companyTypeList", companyTypeList)
                .setParameterList("cityIdList", cityIdList)
                .setParameter("commericalRegistrationNum", commericalRegistrationNum)
                .setParameter("partyGroupName", partyGroupName)
                .setParameter("partySizeId", partySizeId)
                .setParameter("companyId", companyId)
                .list();

        if (resultList.size() > 0) {
            return (List<ToBiller>) resultList;
        }
        return null;
    }

    @Override
    public List<ToBiller> findAll(ToBiller toBiller) {
        String commericalRegistrationNum = toBiller.getCommericalRegistrationNum();
        String partyGroupName = toBiller.getPartyGroupName();
        String companyId = toBiller.getCompanyId();
        String partySizeId = "";

        if (commericalRegistrationNum == null || commericalRegistrationNum.isEmpty()) {
            commericalRegistrationNum = "";
        }
        if (partyGroupName == null || partyGroupName.isEmpty()) {
            partyGroupName = "";
        }
        System.out.println("partySizeId     **" + toBiller.getPartySizeId());
        if (toBiller.getPartySizeId() != null) {
            if (!toBiller.getPartySizeId().equals("")) {
                partySizeId = String.valueOf(toBiller.getPartySizeId());
            }
        }

        List companyTypeList = toBiller.getCompanyTypeList();
        List cityIdList = toBiller.getCityIdList();
        System.out.println("companyTypeList     **" + companyTypeList);

        List<ToBiller> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT G.PARTY_ID partyId,V.PARTY_FAV partyFav,G.TRADE_NAME trandName,\n"
                        + "G.COMPANY_TYPE companyType,CT.DESCRIPTION companyTypeDesc,G.COMMERICAL_REGISTRATION_NUM commericalRegistrationNum,\n"
                        + "G.PARTY_GROUP_NAME partyGroupName,G.PARTY_SIZE_ID partySizeId,PS.DESCRIPTION partySizeDesc,P.CITY_ID cityId,\n"
                        + "C.DESCRIPTION cityDesc,CN.DESCRIPTION countryDesc,G.COMPANY_ID companyId,UL.USER_LOGIN_ID userLoginId\n"
                        + "FROM PARTY_GROUP G\n"
                        + "LEFT OUTER JOIN PARTY_GROUP_FAV V ON G.PARTY_ID = V.PARTY_FAV\n"
                        + "Inner JOIN PARTY P ON G.PARTY_ID = P.PARTY_ID\n"
                        + "Inner Join COMPANY_TYPE CT on G.COMPANY_TYPE = CT.ID\n"
                        + "Inner Join PARTY_SIZE PS on G.PARTY_SIZE_ID = PS.PARTY_SIZE_ID\n"
                        + "Inner Join CITY C on P.CITY_ID = C.CITY_ID\n"
                        + "Inner Join COUNTRY CN on C.COUNTRY_ID = CN.COUNRTY_ID\n"
                        + "Inner Join USER_LOGIN UL on V.PARTY_FAV =UL.PARTY_ID\n"
                        + "WHERE G.COMPANY_TYPE in (:companyTypeList)\n"
                        + "And P.CITY_ID in (:cityIdList)\n"
                        + "And UPPER(G.PARTY_GROUP_NAME) like UPPER('%' || :partyGroupName || '%')\n"
                        + "And G.COMMERICAL_REGISTRATION_NUM like '%' || :commericalRegistrationNum || '%'\n"
                        + "And G.COMPANY_ID like '%' || :companyId || '%'\n"
                        + "And G.PARTY_SIZE_ID = nvl(:partySizeId,G.PARTY_SIZE_ID)")
                .addScalar("partyId")
                .addScalar("partyFav")
                .addScalar("trandName")
                .addScalar("companyType")
                .addScalar("companyTypeDesc")
                .addScalar("commericalRegistrationNum")
                .addScalar("partyGroupName")
                .addScalar("partySizeId")
                .addScalar("partySizeDesc")
                .addScalar("cityId")
                .addScalar("cityDesc")
                .addScalar("countryDesc")
                .addScalar("companyId")
                .addScalar("userLoginId")
                .setResultTransformer(Transformers.aliasToBean(ToBiller.class))
                .setParameterList("companyTypeList", companyTypeList)
                .setParameterList("cityIdList", cityIdList)
                .setParameter("commericalRegistrationNum", commericalRegistrationNum)
                .setParameter("partyGroupName", partyGroupName)
                .setParameter("partySizeId", partySizeId)
                .setParameter("companyId", companyId)
                .list();

        if (resultList.size() > 0) {
            return (List<ToBiller>) resultList;
        }
        return null;
    }

    @Override
    public List<ToBiller> findNonSubscribers(ToBiller toBiller) {
        String commericalRegistrationNum = toBiller.getCommericalRegistrationNum();
        String partyGroupName = toBiller.getPartyGroupName();
        String companyId = toBiller.getCompanyId();
        String partySizeId = "";

        if (commericalRegistrationNum == null || commericalRegistrationNum.isEmpty()) {
            commericalRegistrationNum = "";
        }
        if (partyGroupName == null || partyGroupName.isEmpty()) {
            partyGroupName = "";
        }
        System.out.println("partySizeId     **" + toBiller.getPartySizeId());
        if (toBiller.getPartySizeId() != null) {
            if (!toBiller.getPartySizeId().equals("")) {
                partySizeId = String.valueOf(toBiller.getPartySizeId());
            }
        }

        List companyTypeList = toBiller.getCompanyTypeList();
        List cityIdList = toBiller.getCityIdList();
        System.out.println("companyTypeList     **" + companyTypeList);

        List<ToBiller> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT G.PARTY_ID partyId,V.PARTY_FAV partyFav,G.TRADE_NAME trandName,\n"
                        + "G.COMPANY_TYPE companyType,CT.DESCRIPTION companyTypeDesc,G.COMMERICAL_REGISTRATION_NUM commericalRegistrationNum,\n"
                        + "G.PARTY_GROUP_NAME partyGroupName,G.PARTY_SIZE_ID partySizeId,PS.DESCRIPTION partySizeDesc,P.CITY_ID cityId,\n"
                        + "C.DESCRIPTION cityDesc,CN.DESCRIPTION countryDesc,G.COMPANY_ID companyId,UL.USER_LOGIN_ID userLoginId\n"
                        + "FROM PARTY_GROUP G\n"
                        + "LEFT OUTER JOIN PARTY_GROUP_FAV V ON G.PARTY_ID = V.PARTY_FAV\n"
                        + "INNER JOIN PARTY P ON G.PARTY_ID = P.PARTY_ID\n"
                        + "Inner Join COMPANY_TYPE CT on G.COMPANY_TYPE = CT.ID\n"
                        + "Inner Join PARTY_SIZE PS on G.PARTY_SIZE_ID = PS.PARTY_SIZE_ID\n"
                        + "Inner Join CITY C on P.CITY_ID = C.CITY_ID\n"
                        + "Inner Join COUNTRY CN on C.COUNTRY_ID = CN.COUNRTY_ID\n"
                        + "Inner Join USER_LOGIN UL on V.PARTY_FAV =UL.PARTY_ID\n"
                        + "WHERE G.PARTY_ID NOT IN (SELECT PARTY_FAV FROM PARTY_GROUP_FAV)\n"
                        + "And G.COMPANY_TYPE in (:companyTypeList)\n"
                        + "And P.CITY_ID in (:cityIdList)\n"
                        + "And UPPER(G.PARTY_GROUP_NAME) like UPPER('%' || :partyGroupName || '%')\n"
                        + "And G.COMMERICAL_REGISTRATION_NUM like '%' || :commericalRegistrationNum || '%'\n"
                        + "And G.COMPANY_ID like '%' || :companyId || '%'\n"
                        + "And G.PARTY_SIZE_ID = nvl(:partySizeId,G.PARTY_SIZE_ID)")
                .addScalar("partyId")
                .addScalar("partyFav")
                .addScalar("trandName")
                .addScalar("companyType")
                .addScalar("companyTypeDesc")
                .addScalar("commericalRegistrationNum")
                .addScalar("partyGroupName")
                .addScalar("partySizeId")
                .addScalar("partySizeDesc")
                .addScalar("cityId")
                .addScalar("cityDesc")
                .addScalar("countryDesc")
                .addScalar("companyId")
                .addScalar("userLoginId")
                .setResultTransformer(Transformers.aliasToBean(ToBiller.class))
                .setParameterList("companyTypeList", companyTypeList)
                .setParameterList("cityIdList", cityIdList)
                .setParameter("commericalRegistrationNum", commericalRegistrationNum)
                .setParameter("partyGroupName", partyGroupName)
                .setParameter("partySizeId", partySizeId)
                .setParameter("companyId", companyId)
                .list();

        if (resultList.size() > 0) {
            return (List<ToBiller>) resultList;
        }
        return null;
    }

    @Override
    public List<ToPerson> findAllPerson(ToPerson toPerson) {

        String name = toPerson.getName();
        if (name == null || name.isEmpty()) {
            name = "";
        }
        String referenceNumber = (String) toPerson.getReferenceNumber();
        if (referenceNumber == null || referenceNumber.isEmpty()) {
            referenceNumber = "";
        }
        String gender = String.valueOf(toPerson.getGender());
        if (gender == null || gender.isEmpty()) {
            gender = "";
        }

        List cityIdList = toPerson.getCityIdList();
        System.out.println("cityIdList **" + cityIdList.size());

        List<ToPerson> resultList = sessionFactory.getCurrentSession()
                .createSQLQuery("Select * from(\n"
                        + "Select P.TO_PARTY_ID partyId,G.Trade_Name as name,Py.PARTY_TYPE partyType,\n"
                        + "I.REFERENCE_NUMBER referenceNumber,\n"
                        + "G.COMPANY_TYPE companyType,CT.DESCRIPTION companyTypeDesc,G.COMMERICAL_REGISTRATION_NUM nationalNumber,\n"
                        + "'X' gender,Py.CITY_ID cityId,C.DESCRIPTION cityDesc,CN.DESCRIPTION countryDesc,UL.USER_LOGIN_ID userLoginId\n"
                        + "From Party Py,INVOICE I, PARTY_PROFILE_PREF P,Party_GROUP G,COMPANY_TYPE CT,CITY C,COUNTRY CN,USER_LOGIN UL\n"
                        + "Where Py.PARTY_ID = P.TO_PARTY_ID\n"
                        + "And Py.PARTY_ID = G.PARTY_ID\n"
                        + "And I.REFERENCE_NUMBER = P.SUBSCRIPTION_NO\n"
                        + "And G.COMPANY_TYPE = CT.ID\n"
                        + "And Py.CITY_ID = C.CITY_ID\n"
                        + "And C.COUNTRY_ID = CN.COUNRTY_ID\n"
                        + "And Py.PARTY_ID = UL.PARTY_ID\n"
                        + "And Py.PARTY_TYPE = 'PARTY_GROUP'\n"
                        + "UNION\n"
                        + "Select P.TO_PARTY_ID partyId,(G.FISRT_NAME || ' ' ||G.LAST_NAME) as name,Py.PARTY_TYPE partyType,\n"
                        + "I.REFERENCE_NUMBER referenceNumber,\n"
                        + "NULL companyType,NULL companyTypeDesc,NULL nationalNumber,\n"
                        + "GENDER gender,Py.CITY_ID cityId,C.DESCRIPTION cityDesc,CN.DESCRIPTION countryDesc,UL.USER_LOGIN_ID userLoginId\n"
                        + "From Party Py,INVOICE I, PARTY_PROFILE_PREF P,PERSON G,CITY C,COUNTRY CN,USER_LOGIN UL\n"
                        + "Where Py.PARTY_ID = P.TO_PARTY_ID\n"
                        + "And Py.PARTY_ID = G.PARTY_PARTY_ID\n"
                        + "And I.REFERENCE_NUMBER = P.SUBSCRIPTION_NO\n"
                        + "And Py.CITY_ID = C.CITY_ID\n"
                        + "And C.COUNTRY_ID = CN.COUNRTY_ID\n"
                        + "And Py.PARTY_ID = UL.PARTY_ID\n"
                        + "And Py.PARTY_TYPE = 'PERSON')\n"
                        + "where UPPER(name) like UPPER('%' || :trandName || '%')\n"
                        + "and UPPER(referenceNumber) like UPPER('%' || :referenceNumber || '%')\n"
                        + "and gender = NVL(:gender,gender)\n"
                        + "And cityId in (:cityIdList)")
                .addScalar("partyId")
                .addScalar("name")
                .addScalar("partyType")
                .addScalar("referenceNumber")
                .addScalar("gender")
                .addScalar("companyType")
                .addScalar("companyTypeDesc")
                .addScalar("cityId")
                .addScalar("cityDesc")
                .addScalar("countryDesc")
                .addScalar("nationalNumber")
                .addScalar("userLoginId")
                .setResultTransformer(Transformers.aliasToBean(ToPerson.class))
                .setParameter("trandName", name)
                .setParameter("referenceNumber", referenceNumber)
                .setParameter("gender", gender)
                .setParameterList("cityIdList", cityIdList)
                .list();

        if (resultList.size() > 0) {
            return (List<ToPerson>) resultList;
        }
        return null;
    }

}
