//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.RestAnnotations;
import com.google.gson.annotations.Expose;

@RestAnnotations(path = "/hub/v1/campaigns/{id}",
                 primaryKey = "id",
                 collectionKey = "items")
public class ETCampaign extends ETRestObject {
    @Expose
    private String name = null;
    @Expose
    private String description = null;
    @Expose
    private String campaignCode = null;
    @Expose
    private String color = null;
    @Expose
    private Boolean favorite = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public static ETResponse<ETCampaign> create(ETClient client,
                                                ETCampaign campaign)
        throws ETSdkException
    {
        return ETRestObject.create(client, campaign);
    }

    public static ETResponse<ETCampaign> retrieve(ETClient client)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, ETCampaign.class);
    }

    public static ETResponse<ETCampaign> retrieve(ETClient client,
                                                  Integer page,
                                                  Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, page, pageSize, ETCampaign.class);
    }

    public static ETResponse<ETCampaign> retrieve(ETClient client,
                                                  String filter)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, ETCampaign.class);
    }

    public static ETResponse<ETCampaign> retrieve(ETClient client,
                                                  String filter,
                                                  Integer page,
                                                  Integer pageSize)
        throws ETSdkException
    {
        return ETRestObject.retrieve(client, filter, page, pageSize, ETCampaign.class);
    }

    public ETResponse<ETCampaign> update(ETClient client)
        throws ETSdkException
    {
        return super.update(client);
    }

    public ETResponse<ETCampaign> delete(ETClient client)
        throws ETSdkException
    {
        return super.delete(client);
    }

    public static ETResponse<ETCampaign> delete(ETClient client,
                                                String filter)
        throws ETSdkException
    {
        return ETRestObject.delete(client, filter, ETCampaign.class);
    }

    @Override
    public String toString() {
        toStringOpen();
        toStringAppend("name", getName());
        toStringAppend("description", getDescription());
        toStringAppend("campaignCode", getCampaignCode());
        toStringAppend("color", getColor());
        toStringAppend("favorite", getFavorite());
        toStringClose();
        return getToString();
    }
}