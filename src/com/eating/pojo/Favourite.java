package com.eating.pojo;

public class Favourite {
    private Integer userId;
    private Integer foodId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "userId=" + userId +
                ", foodId=" + foodId +
                '}';
    }
}
