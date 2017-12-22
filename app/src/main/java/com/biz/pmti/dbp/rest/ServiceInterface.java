package com.biz.pmti.dbp.rest;


public interface ServiceInterface {
//
//
////----------------------USER Controllers
//
////old password:    YWRtaW5AYmlyLmdvdi5waDpxd2VydHkxMjMxMjM=
//
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.userinformation/get-user-information-by-username-and-password")
//    Observable<UserInformationTable> getUserInformation(@Query("stringObject") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.municipalitycodes/get-all-assign-munc-for-mobile")
//    Observable<List<UserAssignMunicipality>> getUserAssignMunc(@Query("stringObject") String request);
//
//
////    @Headers({"Content-Type: application/json","Authorization: Basic YmF0YW5nMWtfZGV2dXNlcjpiYXRhbmcxa19kZXZwYXNz"})
////    @POST("api/WLs/")
////    Observable<UserModel> postUser(@Body UserModel jsonArray);
//
//    //----------------------END
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.listofcollection/list-collection-total")
//    Observable<LCSummary> getLCSummary(@Query("stringObjects") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.manualreceiptseries/get-manual-series-by-rdo-code-and-rco-code")
//    Observable<ArrayList<ManualSeriesResponse>> getManualSeries(@Query("stringObject") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.listofcollection/generate-list-collection-v2")
//    Observable<List<LCTotal>> getLCTotalSummary(@Query("stringObjects") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.listofcollection/save-list-collection")
//    Observable<List<LCSave>> saveLC(@Query("stringObjects") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/re-print-lc-last")
//    Observable<List<ReprintLcLast>> getreprintLCLast(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/reprint-receipt-last")
//    Observable<List<ReprintRevenueCollectionLast>> getreprintReceiptLast(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/print-update-sp")
//    Observable<List<PrintUpdate>> getPrintUpdate(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/reprint-receipt-s-pv2")
//    Observable<List<ReceiptNumber>> getreprintReceiptNumber(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/reprint-receipt-view")
//    Observable<List<ReprintRevenueCollection>> getreprintReceiptView(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/save-tax-payment")
//    Observable<List<TaxPaymentOnline>> insertTableTaxPayment_remote(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/reset-password")
//    Observable<ResetPassword> resetPassword(@Query("stringObjects") String request);
//
//
//    /**
//     * with Temporary auth
//     * @param request
//     * @return
//     */
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/login-authenticate-mobile_v2")
//    Observable<UserResponse> LoginUser(@Query("stringObjects") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/forgot-password-req")
//    Observable<OtpResponse> otpUserName(@Query("stringObjects") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/verify-sm-sotp")
//    Observable<OtpResponse> otpVerifyCode(@Query("stringObjects") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/set-new-password-bridge")
//    Observable<OtpResponse> forgotPassword(@Query("stringObjects") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.userinformation/forgotpassby-q-ans")
//    Observable<OtpResponse> otpQNS(@Query("stringObjects") String request);
//
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.taxtype/get-all-tax-type")
//    Observable<List<TaxTypeModel>> getAllTaxType();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.municipalitycodes/get-all-municipality-codes")
//    Observable<List<MunicipalityCode>> getAllMunicipalities();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.birformnumbers/get-all-bir-form-numbers")
//    Observable<List<BirFormNumber>> getAllBirFormNumbers();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.accountcodes/get-all-account-codes")
//    Observable<List<AccountCode>> getAllAccountCodes();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.mannerofpayment/get-all-manner-of-payment")
//    Observable<List<MannerOfPayment>> getAllMannerOfPayment();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.typeofpayment/get-all-type-of-payment")
//    Observable<List<TypeOfPayment>> getAllTypeOfPayment();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.modeofpayment/get-all-mode-of-payment")
//    Observable<List<ModeOfPayment>> getAllModeOfPayment();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.agencycodes/get-all-agency-codes")
//    Observable<List<AgencyCode>> getAllAgencyCodes();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.fundcodes/get-all-fund-codes")
//    Observable<List<FundCode>> getAllFundCodes();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.draweebankcodes/get-all-drawee-bank-codes")
//    Observable<List<DraweeBankCode>> getAllDraweeBankCodes();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.reasonfornopayment/get-all-reason-for-no-payment")
//    Observable<List<ReasonForNoPayment>> getAllReasonForNoPayment();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.reasonforcancellation/get-all-reason-for-cancellation")
//    Observable<List<ReasonForCancellation>> getAllReasonForCancellation();
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.detailsofdeposit/get-all-details-of-deposit")
//    Observable<List<DetailsOfDeposit>> getAllDetailsOfDeposit();
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.globalconfig/get-all-global-config-x")
//    Observable<GlobalConfigResponse> getGlobalConfig();
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.userinformation/get-user-information-by-user-id-mobile")
//    Observable<CheckLoginStatus> getLoginStatus(@Query("stringObject") String request);
//
//
//
//    @Multipart
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/add-tax-payment")
//    Observable<List<AddTaxPayment>> addTaxPayment(@Part("stringObjects") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayer/dplc-search")
//    Observable<List<LCSearch>> getLCUpdate(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/re-print-lc-view")
//    Observable<List<ReprintLcLast>> getLCNumber(@Query("stringArray") String request);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.listofcollection/generate-list-collection-dp")
//    Observable<List<LCTotal>> getLCTotalSummaryDP(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.depositedcollectiondetails/save-deposit")
//    Observable<List<UpdateDeposit>> savedeposit(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.depositedcollectiondetails/deposit-mode")
//    Observable<List<UpdateDeposit>> savedepositmode(@Query("stringArray") String request);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.depositedcollectiondetails/deposit-check-mo-update")
//    Observable<List<UpdateDeposit>> checkmoupdate(@Query("stringArray") String request);
//
//
//
//    @Multipart
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.taxpayment/sync-tax-payment")
//    Observable<List<AddTaxPayment>> syncTaxPayment(@Part("stringObjects") String request);
//
////    @GET("posts")
////    Observable<List<Post>> getPostByUserId(@Query("userId") int userId);
////
////    @GET("posts/{userId}")
////    Observable<Post> getPostByPostId(@Path("userId") int userId);
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.taxpayment/get-check")
//    Observable<List<String>> hasCheck(@Query("dbcCode") String dbcCode, @Query("checkNo") String checkNo);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @GET("api/jsonws/Mrcos-services-portlet.taxpayment/get-mo")
//    Observable<List<String>> hasMo(@Query("ptmoNo") String ptmoNo);
//
//
//    @Headers({"Authorization: Basic YWRtaW46cXdlcnR5MTIzMTIz"})
//    @POST("api/jsonws/Mrcos-services-portlet.manualreceiptseries/update-manual-series-mobile")
//    Observable<ManualSeriesWebResponse> updateManualSeriesWeb(@Query("stringObjects") String toReturn);
//
}
