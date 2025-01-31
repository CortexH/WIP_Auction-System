package com.demo.configurations;

public class EndpointsInformation {

    public static final String[] NoAuthorizationRequiredEndpoints = {
            "/user/login", "/user/register"
    };

    public static final String[] BuyerAuthorizationRequiredEndpoints = {
            "/bids", "/bids/users/*"
    };

    public static final String[] AuctioneerAuthorizationRequiredEndpoints = {
            "/bids/*", "/auctions/new", "/auctions/*/close",
            "/auctions/*"
    };

    public static final String[] OnlyForAdminEndpoints = {

    };

}
