package cl.avenegasm.terremoto.terremotoapi.dto

import spock.lang.*

class PropertieDtoTest extends Specification {
    PropertieDto propertieDto = new PropertieDto()

    def "test set Mag"() {
        when:
        propertieDto.setMag("mag")

        then:
        propertieDto.getMag() == "mag"
    }

    def "test set Place"() {
        when:
        propertieDto.setPlace("place")

        then:
        propertieDto.getPlace() == "place"
    }

    def "test set Time"() {
        when:
        propertieDto.setTime("time")

        then:
        propertieDto.getTime() == "time"
    }

    def "test set Updated"() {
        when:
        propertieDto.setUpdated("updated")

        then:
        propertieDto.getUpdated() == "updated"
    }

    def "test set Tz"() {
        when:
        propertieDto.setTz("tz")

        then:
        propertieDto.getTz() == "tz"
    }

    def "test set Url"() {
        when:
        propertieDto.setUrl("url")

        then:
        propertieDto.getUrl() == "url"
    }

    def "test set Detail"() {
        when:
        propertieDto.setDetail("detail")

        then:
        propertieDto.getDetail() == "detail"
    }

    def "test set Felt"() {
        when:
        propertieDto.setFelt("felt")

        then:
        propertieDto.getFelt() == "felt"
    }

    def "test set Cdi"() {
        when:
        propertieDto.setCdi("cdi")

        then:
        propertieDto.getCdi() == "cdi"
    }

    def "test set Mmi"() {
        when:
        propertieDto.setMmi("mmi")

        then:
        propertieDto.getMmi() == "mmi"
    }

    def "test set Alert"() {
        when:
        propertieDto.setAlert("alert")

        then:
        propertieDto.getAlert() == "alert"
    }

    def "test set Status"() {
        when:
        propertieDto.setStatus("status")

        then:
        propertieDto.getStatus() == "status"
    }

    def "test set Tsunami"() {
        when:
        propertieDto.setTsunami("tsunami")

        then:
        propertieDto.getTsunami() == "tsunami"
    }

    def "test set Sig"() {
        when:
        propertieDto.setSig("sig")

        then:
        propertieDto.getSig() == "sig"
    }

    def "test set Net"() {
        when:
        propertieDto.setNet("net")

        then:
        propertieDto.getNet() == "net"
    }

    def "test set Code"() {
        when:
        propertieDto.setCode("code")

        then:
        propertieDto.getCode() == "code"
    }

    def "test set Ids"() {
        when:
        propertieDto.setIds("ids")

        then:
        propertieDto.getIds() == "ids"
    }

    def "test set Sources"() {
        when:
        propertieDto.setSources("sources")

        then:
        propertieDto.getSources() == "sources"
    }

    def "test set Types"() {
        when:
        propertieDto.setTypes("types")

        then:
        propertieDto.getTypes() == "types"
    }

    def "test set Nst"() {
        when:
        propertieDto.setNst("nst")

        then:
        propertieDto.getNst() == "nst"
    }

    def "test set Dmin"() {
        when:
        propertieDto.setDmin("dmin")

        then:
        propertieDto.getDmin() == "dmin"
    }

    def "test set Rms"() {
        when:
        propertieDto.setRms("rms")

        then:
        propertieDto.getRms() == "rms"
    }

    def "test set Gap"() {
        when:
        propertieDto.setGap("gap")

        then:
        propertieDto.getGap() == "gap"
    }

    def "test set Mag Type"() {
        when:
        propertieDto.setMagType("magType")

        then:
        propertieDto.getMagType() == "magType"
    }

    def "test set Type"() {
        when:
        propertieDto.setType("type")

        then:
        propertieDto.getType() == "type"
    }

    def "test set Title"() {
        when:
        propertieDto.setTitle("title")

        then:
        propertieDto.getTitle() == "title"
    }

    def "test can Equal"() {
        when:
        boolean result = propertieDto.canEqual(new PropertieDto())

        then:
        result == true
    }

}