package edu.neu.csye6200.objects;

public class Group {

    private int groupId;
    private int minLimitInMonths;
    private int maxLimitInMonths;
    private int maxGroupSize;
    private int maxGroupsPerRoom;
    private String ageGroupName;
    private int ageGroupId;
    private int classroomId;
    private int maxCapacity;
    private int remainingCapacity;
    private int teacherId;

    public Group(int groupId, int ageGroupId, int classroomId, int maxCapacity, int remainingCapacity, int teacherId) {
        try {
            this.groupId = groupId;
            this.ageGroupId = ageGroupId;
            this.classroomId = classroomId;
            this.maxCapacity = maxCapacity;
            this.remainingCapacity = remainingCapacity;
            this.teacherId = teacherId;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(int ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public Group(String csvInput) {
        String[] inputs = csvInput.split(",");
        try {
            groupId = Integer.parseInt(inputs[0]);
            ageGroupName = inputs[1];
            minLimitInMonths = Integer.parseInt(inputs[2]);
            maxLimitInMonths = Integer.parseInt(inputs[3]);
            maxGroupSize = Integer.parseInt(inputs[4]);
            maxGroupsPerRoom = Integer.parseInt(inputs[5]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(classroomId + " : ");
        output.append(minLimitInMonths + " to " + maxLimitInMonths + " months old ;");
        output.append("maxGroupSize: " + maxGroupSize + " members ;");
        output.append(maxGroupsPerRoom + " groups per room ;");
        return output.toString();
    }

    public int getMinLimitInMonths() {
        return minLimitInMonths;
    }

    public void setMinLimitInMonths(int minLimitInMonths) {
        this.minLimitInMonths = minLimitInMonths;
    }

    public int getMaxLimitInMonths() {
        return maxLimitInMonths;
    }

    public void setMaxLimitInMonths(int maxLimitInMonths) {
        this.maxLimitInMonths = maxLimitInMonths;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public int getMaxGroupsPerRoom() {
        return maxGroupsPerRoom;
    }

    public void setMaxGroupsPerRoom(int maxGroupsPerRoom) {
        this.maxGroupsPerRoom = maxGroupsPerRoom;
    }
}
