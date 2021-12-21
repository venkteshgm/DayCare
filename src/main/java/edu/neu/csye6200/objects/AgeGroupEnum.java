/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.objects;

/**
 *
 * @author jasonpauldarivemula
 */
public enum AgeGroupEnum {
    
    Group1(1,6,12,4,3,"Age Group 1","Group 1 - age group between 6 and 12"),
    Group2(2,13,24,5,3,"Age Group 2","Group 1 - age group between 13 and 24"),
    Group3(3,25,35,6,3,"Age Group 3","Group 1 - age group between 25 and 35"),
    Group4(4,36,47,8,3,"Age Group 4","Group 1 - age group between 36 and 47"),
    Group5(5,48,59,12,2,"Age Group 5","Group 1 - age group between 48 and 59"),
    Group6(6,60,300,15,2,"Age Group 6","Group 1 - age group above 60");
    
       
        private int ageGroupId;
        private int minLimitInMonths;
	private int maxLimitInMonths;
	private int maxGroupSize;
	private int maxGroupsPerRoom;
        private String ageGroupName;
        private String ageGroupDesc;

    private AgeGroupEnum(int ageGroupId,int minLimitInMonths, int maxLimitInMonths, int maxGroupSize, int maxGroupsPerRoom, String ageGroupName, String ageGroupDesc) {
        this.ageGroupId=ageGroupId;
        this.minLimitInMonths = minLimitInMonths;
        this.maxLimitInMonths = maxLimitInMonths;
        this.maxGroupSize = maxGroupSize;
        this.maxGroupsPerRoom = maxGroupsPerRoom;
        this.ageGroupName = ageGroupName;
        this.ageGroupDesc=ageGroupDesc;
       
    }

    public String getAgeGroupName() {
        return ageGroupName;
    }

    public int getAgeGroupId() {
        return ageGroupId;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }
    
    public int getMinLimitInMonths(){
        return minLimitInMonths;
    }
    
    public int getMaxLimitInMonths(){
        return maxLimitInMonths;
    }
    public int getMaxGroupsPerRoom(){
        return maxGroupsPerRoom;
    }
    
    public static AgeGroupEnum getAgeGroupEnum(int ageGroupId){
        for(AgeGroupEnum ageGroup : AgeGroupEnum.values())
        {
            if(ageGroup.getAgeGroupId()==ageGroupId)
                return ageGroup;
        }
        return null;
    }
    
    public static AgeGroupEnum whichAgeGroupForAge(int age){
        //find appropriate AgeGroupEnum for given integer age
        for(AgeGroupEnum ageGroup : AgeGroupEnum.values())
        {
            if(age>=ageGroup.minLimitInMonths && age<=ageGroup.maxLimitInMonths)
                return ageGroup;
        }
        return null;
    }
      
        
}
