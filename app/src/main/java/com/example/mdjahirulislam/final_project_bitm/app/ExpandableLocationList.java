package com.example.mdjahirulislam.final_project_bitm.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mdjahirulislam on 01/06/17.
 */

public class ExpandableLocationList {
    public static HashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableList = new LinkedHashMap<String, List<String>>();
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();



        List<String> listName = new ArrayList<String>();
        listName.add(0,"Select Location Automatic");
        listName.add(1,"Select Location Manually");




        // All Division Name
        List<String> divisionName = new ArrayList<String>();

        divisionName.add(0,"Select Location Automatic");
        divisionName.add(1,"Dhaka");
        divisionName.add(2,"Dhaka Division");
        divisionName.add(3,"Barisal Division");
        divisionName.add(4,"Chitagong Division");
        divisionName.add(5,"Rajshahi Division");
        divisionName.add(6,"Khulna Division");
        divisionName.add(7,"Sylet Division");
        divisionName.add(8,"Rangpur Division");
        divisionName.add(9,"Mymensingh Division");


        List<String> myLocation = new ArrayList<String>();
        myLocation.add("Find My Location");

        //District of Dhaka Division
        List<String> dhakaDivision = new ArrayList<String>();
        dhakaDivision.add("Dhaka");
        dhakaDivision.add("Gazipur");
        dhakaDivision.add("Kishoreganj");
        dhakaDivision.add("Manikgonj");
        dhakaDivision.add("Munshiganj");
        dhakaDivision.add("Narayanganj");
        dhakaDivision.add("Narsingdi");
        dhakaDivision.add("Tangail");
        dhakaDivision.add("Faridpur");
        dhakaDivision.add("Gopalganj");
        dhakaDivision.add("Madaripur");
        dhakaDivision.add("Rajbari");
        dhakaDivision.add("Shariatpur");
        //District of Barisal Division
        List<String> barisalDivision = new ArrayList<String>();
        barisalDivision.add("Barisal");
        barisalDivision.add("Barguna");
        barisalDivision.add("Bhola");
        barisalDivision.add("Jhalokati");
        barisalDivision.add("Patuakhali");
        barisalDivision.add("Pirojpur");
        //District of Chittagong Division
        List<String> chittagongDivision = new ArrayList<String>();
        chittagongDivision.add("Brahmanbaria");
        chittagongDivision.add("Comilla");
        chittagongDivision.add("Chandpur");
        chittagongDivision.add("Lakshmipur");
        chittagongDivision.add("Noakhali");
        chittagongDivision.add("Feni");
        chittagongDivision.add("Khagrachhari");
        chittagongDivision.add("Rangamati");
        chittagongDivision.add("Bandarban");
        chittagongDivision.add("Chittagong");
        chittagongDivision.add("Cox's Bazar");
        //District of Khulna Division
        List<String> khulnaDivision = new ArrayList<String>();
        khulnaDivision.add("Bagerhat");
        khulnaDivision.add("Chuadanga");
        khulnaDivision.add("Jessor");
        khulnaDivision.add("Jhenaidah");
        khulnaDivision.add("Khulna");
        khulnaDivision.add("Kushtia");
        khulnaDivision.add("Magura");
        khulnaDivision.add("Meherpur");
        khulnaDivision.add("Narail");
        khulnaDivision.add("Satkhira");
        ////District of Mymensingh Division
        List<String> mymensinghDivision = new ArrayList<String>();
        mymensinghDivision.add("Mymensingh");
        mymensinghDivision.add("Jamalpur");
        mymensinghDivision.add("Netrokona");
        mymensinghDivision.add("Sherpur");
        ////District of Rajshahai Division
        List<String> rajshahiDivision = new ArrayList<String>();
        rajshahiDivision.add("Bogra");
        rajshahiDivision.add("Chapainawabganj");
        rajshahiDivision.add("Joypurhat");
        rajshahiDivision.add("Naogaon");
        rajshahiDivision.add("Natore");
        rajshahiDivision.add("Pabna");
        rajshahiDivision.add("Rajshahi");
        rajshahiDivision.add("Sirajganj");
        ////District of Rangpur Division
        List<String> rangpurDivision = new ArrayList<String>();
        rangpurDivision.add("Rangpur");
        rangpurDivision.add("Dinajpur");
        rangpurDivision.add("Gaibandha");
        rangpurDivision.add("Kurigram");
        rangpurDivision.add("Lalmonirhat");
        rangpurDivision.add("Nilphamari");
        rangpurDivision.add("Panchagrahm");
        rangpurDivision.add("Thakurgaon");
        //District of Sylhet Division
        List<String> sylhetDivision = new ArrayList<String>();
        sylhetDivision.add("Habiganj");
        sylhetDivision.add("Moulvibazar");
        sylhetDivision.add("Sunamganj");
        sylhetDivision.add("Sylhet");
        //Extra Recommend for Dhaka District
        List<String> upzilaOfDhaka = new ArrayList<String>();
        upzilaOfDhaka.add("Mirpur");
        upzilaOfDhaka.add("Uttara");
        upzilaOfDhaka.add("Dhanmondi");
        upzilaOfDhaka.add("Gulshan");
        upzilaOfDhaka.add("Mohammadpur");
        upzilaOfDhaka.add("Elephant Road");
        upzilaOfDhaka.add("Savar");
        upzilaOfDhaka.add("Motijheel");
        upzilaOfDhaka.add("Jatrabari");
        upzilaOfDhaka.add("Badda");
        upzilaOfDhaka.add("Basundhara");
        upzilaOfDhaka.add("Tejgaon");
        upzilaOfDhaka.add("Rampura");
        upzilaOfDhaka.add("Baridhara");
        upzilaOfDhaka.add("Khilgaon");
        upzilaOfDhaka.add("Banani");
        upzilaOfDhaka.add("Paltan");
        upzilaOfDhaka.add("Malibag");
        upzilaOfDhaka.add("Kawranbazar");
        upzilaOfDhaka.add("Mogbazar");
        upzilaOfDhaka.add("Keranigog");
        upzilaOfDhaka.add("Khilkhet");
        upzilaOfDhaka.add("Wari");
        upzilaOfDhaka.add("Lalbag");
        upzilaOfDhaka.add("Tongi");
        upzilaOfDhaka.add("Purbachal");
        upzilaOfDhaka.add("Mohakhali");
        upzilaOfDhaka.add("Cantonment");
        upzilaOfDhaka.add("Sutrapur");
        upzilaOfDhaka.add("Bangshal");
        upzilaOfDhaka.add("Banglamotor");
        upzilaOfDhaka.add("Ramna");
        upzilaOfDhaka.add("Basabo");
        upzilaOfDhaka.add("Demra");
        upzilaOfDhaka.add("New Market");
        upzilaOfDhaka.add("Chaukbazar");
        upzilaOfDhaka.add("Kamrangirchar");
        upzilaOfDhaka.add("Hazaribag");
        upzilaOfDhaka.add("Kotowali");
        upzilaOfDhaka.add("Kafrul");
        upzilaOfDhaka.add("Mohakhali DOHS");
        upzilaOfDhaka.add("Mirpur DOHS");
        upzilaOfDhaka.add("Shajahanpur");
        upzilaOfDhaka.add("Dhamrai");
        upzilaOfDhaka.add("Nawabganj");
        upzilaOfDhaka.add("Dohar");
        upzilaOfDhaka.add("Banani DOHS");
//        List<String> other = new ArrayList<String>();
//        expandableListDetail.put("Division Name", divisionName);
        expandableListDetail.put(divisionName.get(0), myLocation);
        expandableListDetail.put(divisionName.get(1), upzilaOfDhaka);
        expandableListDetail.put(divisionName.get(2), dhakaDivision);
        expandableListDetail.put(divisionName.get(3), barisalDivision);
        expandableListDetail.put(divisionName.get(4), chittagongDivision);
        expandableListDetail.put(divisionName.get(5), rajshahiDivision);
        expandableListDetail.put(divisionName.get(6), khulnaDivision);
        expandableListDetail.put(divisionName.get(7), sylhetDivision);
        expandableListDetail.put(divisionName.get(8), rangpurDivision);
        expandableListDetail.put(divisionName.get(9), mymensinghDivision);


//
//        expandableList.put(listName.get(0),myLocation);
//        expandableList.put(listName.get(1),divisionName);

//        expandableListDetail.put("Other", other);
        return expandableListDetail;
    }
}
