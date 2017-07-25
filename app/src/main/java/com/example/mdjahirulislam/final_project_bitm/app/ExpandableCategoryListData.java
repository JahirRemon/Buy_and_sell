package com.example.mdjahirulislam.final_project_bitm.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mdjahirulislam on 28/05/17.
 */

public class ExpandableCategoryListData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> electronic = new ArrayList<String>();
        electronic.add("Mobile Phones");
        electronic.add("Mobile Phone Accessories");
        electronic.add("Computer & Tablets");
        electronic.add("Computer Accessories");
        electronic.add("TVs");
        electronic.add("Cameras");
        electronic.add("Lighting");
        electronic.add("Audio & mp3");
        electronic.add("Video Games & Consoles");
        electronic.add("Other Electronics");

        List<String> cars_vehicles = new ArrayList<String>();
        cars_vehicles.add("Cars");
        cars_vehicles.add("Motorbikes & Scooters");
        cars_vehicles.add("Bicycles");
        cars_vehicles.add("Trucks, Vans & Buses");
        cars_vehicles.add("Auto Services");

        List<String> property = new ArrayList<String>();
        property.add("Apartment & Flats");
        property.add("House");
        property.add("Plots & Land");
        property.add("Rooms");
        property.add("Garages");
        property.add("Commercial Property");


        List<String> services = new ArrayList<String>();
        services.add("Business & Technical Services");
        services.add("Travel & Visa");
        services.add("Tickets");
        services.add("Event & Hospitality");
        services.add("Health & Lifestyle");

        List<String> home_garden = new ArrayList<String>();
        home_garden.add("Furniture");
        home_garden.add("Home Application");
        home_garden.add("Electricity, AC, Bathroom & Garden");
        home_garden.add("Other Home Items");

        List<String> education = new ArrayList<String>();
        education.add("Textbooks");
        education.add("Tuition");
        education.add("Study Abroad");
        education.add("Other Education");

        List<String> clothingHealthBeauty = new ArrayList<String>();
        clothingHealthBeauty.add("Clothing");
        clothingHealthBeauty.add("Shoes & Footwear");
        clothingHealthBeauty.add("Jewellery");
        clothingHealthBeauty.add("Optical Items");
        clothingHealthBeauty.add("Watches");
        clothingHealthBeauty.add("Bags");
        clothingHealthBeauty.add("Other Personal Items");

//        List<String> other = new ArrayList<String>();

        expandableListDetail.put("Electronics", electronic);
        expandableListDetail.put("Cars & Vehicles", cars_vehicles);
        expandableListDetail.put("Property", property);
        expandableListDetail.put("Services", services);
        expandableListDetail.put("Home & Garden", home_garden);
        expandableListDetail.put("Education", education);
        expandableListDetail.put("Clothing, Health & Beauty", clothingHealthBeauty);
//        expandableListDetail.put("Other", other);
        return expandableListDetail;
    }

}
