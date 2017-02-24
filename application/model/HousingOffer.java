package model;

import java.util.Date;

/**
 * A class for representing housing offer with the housing and owner id. It has 3 different state
 * State 1: exist (housing and owner id), state 2: available (begin and end date are fulfiled), state 3: booked (the idGuest is fulfiled)
 * @since February, 2017
 */
public class HousingOffer {

    protected long beginDate;
    protected long endDate;
    protected long idHousing;
    protected long idOwner;
    protected long idGuest;
    protected Long id;

    /**
     * Empty constructor for hibernate
     */
    public HousingOffer()
    {
    }

    /**
     * Build a house with a person.
     * @param idHousing The id of the house
     * @param idOwner The id of the house owner
     */
    public HousingOffer(long idHousing, long idOwner) {
        this.idHousing = idHousing;
        this.idOwner = idOwner;
        //nonsens value for check the state of the offer:
        this.beginDate = -1;
        this.endDate = -1;
        this.idGuest = -1;
    }

    /**
     * Return the id of the housing offer
     * @return <Long> id
     */
    public Long getId() {
        return id;
    }
    /**
     * Set the id of the housing offer (only use by hybernate)
     * @param <Long> id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Return the timestamp of when the offer start
     * @return <long> timestamp
     */
    public long getBeginDate() {
        return this.beginDate;
    }
    /**
     * set the timestamp of when the offer start
     * @param <long> timestamp
     */
    public void setBeginDate(long timestamp) {
        this.beginDate = timestamp;
    }
    /**
     * Return the timestamp of when the offer start
     * @return <Date> Date
     */
    public Date getBeginDateObject() {
        return this.beginDate != -1 ? new Date(this.beginDate) : null;
    }
    /**
     * Return the timestamp of when the offer end
     * @return <long> timestamp
     */
    public long getEndTimestamp() {
        return this.endDate;
    }
    /**
     * set the timestamp of when the offer end
     * @param <long> timestamp
     */
    public void setEndDate(long timestamp) {
        this.beginDate = timestamp;
    }
    /**
     * Return the date of when the offer finish
     * @return <Date> Date
     */
    public Date getEndDate() {
        return this.endDate != -1 ? new Date(this.endDate) : null;
    }
    /**
     * Return id of the house in db
     * @return <long> house adress
     */
    public long getIdHousing() {
        return this.idHousing;
    }
    /**
     * Return the id of the house owner
     * @return <long> owner id
     */
    public long getIdOwner() {
        return this.idOwner;
    }
    /**
     * Return the id of the house guest
     * @return <long> guest id
     */
    public long getIdGuest() {
        return this.idGuest;
    }
    /**
     * Set the guest id
     * @param <long> the guest id
     */
    public void setIdGuest(long idGuest) {
        this.idGuest = idGuest;
    }
    /**
     * Check if the housingOffer is in state 2 (offer but no guest)
     * @return <boolean>
     */
    public boolean isRegistred() {
        return (this.idGuest == -1 && this.beginDate == -1);
    }
    /**
     * Check if the housingOffer is in state 2 (offer but no guest)
     * @return <boolean>
     */
    public boolean isAvailable() {
        return (this.idGuest == -1 && this.beginDate != -1);
    }
    /**
     * Check if the housingOffer is in state 3 (offer and reservation)
     * @return <boolean>
     */
    public boolean isBooked() {
        return (this.beginDate != -1 && this.idGuest != -1);
    }

}