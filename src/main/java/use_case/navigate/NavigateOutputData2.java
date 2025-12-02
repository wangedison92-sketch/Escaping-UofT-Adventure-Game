package use_case.navigate;

public class NavigateOutputData2 {
    private final String targetView;

    public NavigateOutputData(String targetViewName) {
        this.targetView = targetViewName;
    }

    public String getTargetView() {
        return targetView;
    }
}
