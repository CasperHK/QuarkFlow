package org.acme.inertia;

import java.util.Map;

public class InertiaPage {

    private String component;
    private Map<String, Object> props;
    private String url;
    private String version;

    public InertiaPage() {
    }

    public InertiaPage(String component, Map<String, Object> props, String url, String version) {
        this.component = component;
        this.props = props;
        this.url = url;
        this.version = version;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
