package com.example.styleout14.PrimoAccesso;

public class RowItem {

    private int ImageId;
    private String ImageName;

    public RowItem(int imageId, String imageName) {
        this.ImageId = imageId;
        this.ImageName = imageName;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }

    public void setImageName(String imageName) {
        this.ImageName = imageName;
    }

    @Override
    public String toString() {
        return ImageName;
    }
}
