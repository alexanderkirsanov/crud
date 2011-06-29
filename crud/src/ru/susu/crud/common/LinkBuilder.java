package ru.susu.crud.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LinkBuilder implements Cloneable {
    private final String targetPage;
    private final Map<String, String> parameters = new HashMap<String, String>();

    public LinkBuilder(String targetPage) {
        this.targetPage = targetPage;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public void addParameters(Map<String, String> parameters) {
        this.parameters.putAll(parameters);
    }

    public void removeParameter(String name) {
        this.parameters.remove(name);
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getLink() {
        StringBuilder stringBuilder = new StringBuilder(targetPage).append(parameters.size() != 0 ? "?" : "");
        Set<Map.Entry<String, String>> setOfParameters = this.parameters.entrySet();
        for (Map.Entry<String, String> entry : setOfParameters) {
            try {
                stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                //I hope it is not possible
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        LinkBuilder linkBuilder = new LinkBuilder(this.targetPage);
        linkBuilder.addParameters(this.parameters);
        return linkBuilder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkBuilder that = (LinkBuilder) o;

        return !(parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) && !(targetPage != null ? !targetPage.equals(that.targetPage) : that.targetPage != null);

    }

    @Override
    public int hashCode() {
        int result = targetPage != null ? targetPage.hashCode() : 0;
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }
}
