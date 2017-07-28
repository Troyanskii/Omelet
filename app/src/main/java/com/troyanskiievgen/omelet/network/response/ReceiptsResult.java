package com.troyanskiievgen.omelet.network.response;

import com.troyanskiievgen.omelet.model.Receipt;

import java.util.List;

/**
 * Created by Relax on 27.07.2017.
 */

public class ReceiptsResult {

    private String title;
    private float version;
    private String href;
    private List<Receipt> results;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Receipt> getResults() {
        return results;
    }

    public void setResults(List<Receipt> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ReceiptsResult{" +
                "title='" + title + '\'' +
                ", version=" + version +
                ", href='" + href + '\'' +
                ", results=" + results +
                '}';
    }
}
