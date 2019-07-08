package demo.isoft.com.airplan.pojo;

/**
 * Created by hafu_16 on 2019/7/8.
 */

public class FragmentIndexGridviewItem {
    private int imageId;
    private  String title;
    private String subtitle;

    public FragmentIndexGridviewItem(int imageId, String title, String subtitle) {
        this.imageId = imageId;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
