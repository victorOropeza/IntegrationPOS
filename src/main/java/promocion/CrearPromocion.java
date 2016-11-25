package promocion;
public class CrearPromocion 
{
    public String Promocion2x1(String IDArticulo, String VigenciaInicio, String VigenciaFin,
                            String Descripcion, String IDPromo,String X,String Y)
    {
        return Promo2x1(IDArticulo,VigenciaInicio, VigenciaFin,Descripcion, IDPromo,X, Y);
    }
    public String PromocionGift(String IDArticulo, String IDBeneficio, String VigenciaInicio, String VigenciaFin,
                                String Descripcion, String IDPromo)
    {
        return PromoGift(IDArticulo, IDBeneficio,VigenciaInicio, VigenciaFin, Descripcion, IDPromo);
    }
    
    private String Promo2x1(String IDArticulo, String VigenciaInicio, String VigenciaFin,
                            String Descripcion, String IDPromo, String X,String Y) 
    {
        String XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"   <ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
"   <PricingAdjustment id=\""+IDPromo+"\" identifier=\""+Descripcion+"\">\n" +
"        <id>"+IDPromo+"</id>\n" +
"        <client id=\"39363B0921BB4293B48383844325E84C\" entity-name=\"ADClient\" identifier=\"The White Valley Group\"/>\n" +
"        <organization id=\"67839EEFA49E44AC969BD60093FCC899\" entity-name=\"Organization\" identifier=\"The White Valley Group\"/>\n" +
"        <active>true</active>\n" +
"        <name>"+Descripcion+"</name>\n" +
"        <priority>20</priority>\n" +
"        <fixedPrice xsi:nil=\"true\"/>\n" +
"        <startingDate>"+FormatoFecha(VigenciaInicio)+"T00:00:00.0Z</startingDate>\n" +
"        <endingDate>"+FormatoFecha(VigenciaFin)+"</endingDate>\n" +
"        <includedBusinessPartners>Y</includedBusinessPartners>\n" +
"        <includedBPCategories>Y</includedBPCategories>\n" +
"        <includedProducts>N</includedProducts>\n" +
"        <includedProductCategories>Y</includedProductCategories>\n" +
"        <description xsi:nil=\"true\"/>\n" +
"        <includePriceLists>Y</includePriceLists>\n" +
"        <minQuantity xsi:nil=\"true\"/>\n" +
"        <maxQuantity xsi:nil=\"true\"/>\n" +
"        <discountType id=\"E08EE3C23EBA49358A881EF06C139D63\" entity-name=\"PromotionType\" identifier=\"Buy X pay Y of same product\"/>\n" +
"        <applyNext>false</applyNext>\n" +
"        <printName>"+Descripcion+"</printName>\n" +
"        <includedOrganizations>Y</includedOrganizations>\n" +
"        <ismultiple>false</ismultiple>\n" +
"        <multiple xsi:nil=\"true\"/>\n" +
"        <oBDISCX>"+X+"</oBDISCX>\n" +
"        <oBDISCY>"+Y+"</oBDISCY>\n" +
"        <oBDISCSubtype>CHEAPEST</oBDISCSubtype>\n" +
"        <oBDISCDistribute>false</oBDISCDistribute>\n" +
"        <obdiscPrice xsi:nil=\"true\"/>\n" +
"        <oBDISCCCurrency xsi:nil=\"true\"/>\n" +
"        <obdiscUpc xsi:nil=\"true\"/>\n" +
"        <obdiscImage xsi:nil=\"true\"/>\n" +
"        <obdiscAmt xsi:nil=\"true\"/>\n" +
"        <obdiscPercentage xsi:nil=\"true\"/>\n" +
"        <oBDISCIncludedRoles>Y</oBDISCIncludedRoles>\n" +
"        <includedCharacteristics>Y</includedCharacteristics>\n" +
"        <obdiscApprovalRequired>false</obdiscApprovalRequired>\n" +
"        <oBDISCOfferRoleList/>\n" +
"        <pricingAdjustmentBusinessPartnerList/>\n" +
"        <pricingAdjustmentBusinessPartnerGroupList/>\n" +
"        <pricingAdjustmentCharacteristicList/>\n" +
"        <pricingAdjustmentOrganizationList/>\n" +
"        <pricingAdjustmentPriceListList/>\n" +
"        <pricingAdjustmentProductList>\n" +
"            <PricingAdjustmentProduct id=\""+IDPromo+"\" identifier=\""+Descripcion+"\">\n" +
"                <id>"+IDPromo+"</id>\n" +
"                <client id=\"39363B0921BB4293B48383844325E84C\" entity-name=\"ADClient\" identifier=\"The White Valley Group\"/>\n" +
"                <organization id=\"67839EEFA49E44AC969BD60093FCC899\" entity-name=\"Organization\" identifier=\"The White Valley Group\"/>\n" +
"                <active>true</active>\n" +
"                <priceAdjustment id=\""+IDPromo+"\" entity-name=\"PricingAdjustment\" identifier=\""+Descripcion+"\"/>\n" +
"                <product id=\""+IDArticulo+"\" entity-name=\"Product\" identifier=\""+Descripcion+"\"/>\n" +
"                <obdiscIsGift>false</obdiscIsGift>\n" +
"                <obdiscQty xsi:nil=\"true\"/>\n" +
"                <obdiscGifqty xsi:nil=\"true\"/>\n" +
"            </PricingAdjustmentProduct>\n" +
"        </pricingAdjustmentProductList>\n" +
"        <pricingAdjustmentProductCategoryList/>\n" +
"        <pricingAdjustmentTrlList/>\n" +
"    </PricingAdjustment>\n"+
"    </ob:Openbravo>";
        
        
        return XML;
    }
    
    private String PromoGift(String IDArticulo, String IDBeneficio, String VigenciaInicio, String VigenciaFin,
                            String Descripcion, String IDPromo)
    {
        
        String XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"       <ob:Openbravo xmlns:ob=\"http://www.openbravo.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
"       <PricingAdjustment id=\""+IDPromo+"\" identifier=\""+Descripcion+"\">\n" +
"        <id>"+IDPromo+"</id>\n" +
"        <client id=\"39363B0921BB4293B48383844325E84C\" entity-name=\"ADClient\" identifier=\"The White Valley Group\"/>\n" +
"        <organization id=\"67839EEFA49E44AC969BD60093FCC899\" entity-name=\"Organization\" identifier=\"The White Valley Group\"/>\n" +
"        <active>true</active>\n" +
"        <name>"+Descripcion+"</name>\n" +
"        <startingDate>"+FormatoFecha(VigenciaInicio)+"T00:00:00.0Z</startingDate>\n" +
"        <endingDate>"+FormatoFecha(VigenciaFin)+"T00:00:00.0Z</endingDate>\n" +
"        <includedBusinessPartners>Y</includedBusinessPartners>\n" +
"        <includedBPCategories>Y</includedBPCategories>\n" +
"        <includedProducts>N</includedProducts>\n" +
"        <includedProductCategories>Y</includedProductCategories>\n" +
"        <includePriceLists>Y</includePriceLists>\n" +
"        <discountType id=\"94AEA884F5AD4EABB72322832B9C5172\" entity-name=\"PromotionType\" identifier=\"Buy X and get Y as gift\"/>\n" +
"        <applyNext>false</applyNext>\n" +
"        <includedOrganizations>Y</includedOrganizations>\n" +
"        <ismultiple>false</ismultiple>\n" +
"        <oBDISCDistribute>true</oBDISCDistribute>\n" +
"        <oBDISCIncludedRoles>Y</oBDISCIncludedRoles>\n" +
"        <includedCharacteristics>Y</includedCharacteristics>\n" +
"        <obdiscApprovalRequired>false</obdiscApprovalRequired>\n" +
"        <oBDISCOfferRoleList/>\n" +
"        <pricingAdjustmentBusinessPartnerList/>\n" +
"        <pricingAdjustmentBusinessPartnerGroupList/>\n" +
"        <pricingAdjustmentCharacteristicList/>\n" +
"        <pricingAdjustmentOrganizationList/>\n" +
"        <pricingAdjustmentPriceListList/>\n" +
"        <pricingAdjustmentProductList>\n" +
"            <PricingAdjustmentProduct id=\""+IDPromo+"\" identifier=\""+Descripcion+"\">\n" +
"                <id>"+IDPromo+"</id>\n" +
"                <client id=\"39363B0921BB4293B48383844325E84C\" entity-name=\"ADClient\" identifier=\"The White Valley Group\"/>\n" +
"                <organization id=\"67839EEFA49E44AC969BD60093FCC899\" entity-name=\"Organization\" identifier=\"The White Valley Group\"/>\n" +
"                <active>true</active>\n" +
"                <priceAdjustment id=\""+IDPromo+"\" entity-name=\"PricingAdjustment\" identifier=\""+Descripcion+"\"/>\n" +
"                <product id=\""+IDArticulo+"\" entity-name=\"Product\" identifier=\"Axion 750\"/>\n" +
"                <obdiscIsGift>false</obdiscIsGift>\n" +
"            </PricingAdjustmentProduct>\n" +
"            <PricingAdjustmentProduct id=\""+IDPromo+"\" identifier=\""+Descripcion+"\">\n" +
"                <id>"+IDPromo+"</id>\n" +
"                <client id=\"39363B0921BB4293B48383844325E84C\" entity-name=\"ADClient\" identifier=\"The White Valley Group\"/>\n" +
"                <organization id=\"67839EEFA49E44AC969BD60093FCC899\" entity-name=\"Organization\" identifier=\"The White Valley Group\"/>\n" +
"                <active>true</active>\n" +
"                <priceAdjustment id=\""+IDPromo+"\" entity-name=\"PricingAdjustment\" identifier=\""+Descripcion+"\"/>\n" +
"                <product id=\""+IDBeneficio+"\" entity-name=\"Product\" identifier=\""+Descripcion+"\"/>\n" +
"                <obdiscIsGift>true</obdiscIsGift>\n" +
"            </PricingAdjustmentProduct>\n" +
"        </pricingAdjustmentProductList>\n" +
"        <pricingAdjustmentProductCategoryList/>\n" +
"        <pricingAdjustmentTrlList/>\n" +
"    </PricingAdjustment>\n" +
"    </ob:Openbravo>";
        
        
        return XML;
    }
    
    private String FormatoFecha(String Fecha)
    {
      return Fecha.substring(0,4)+"-"+Fecha.substring(4,6)+"-"+Fecha.substring(6,8);
    }
    
}
