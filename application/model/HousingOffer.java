package model;

import java.util.Date;

/**
 * A class for representing housing offer with a begin and end date, the owner id, the housing id and when booked the guest id.
 * @since February, 2017
 */
public class HousingOffer {

    protected long beginDate;
    protected long endDate;
    protected long idHousing;
    protected long idOwner;
    protected long idGuest;

    /**
     * Build a new housing offer associated with a owner and his house.
     * When idGuest is null it's mean there's no user who take the offer. By default idGuest is always null.
     * @param beginDate The timestamp of when the offer start
     * @param endDate The timestamp of when the offer end
     * @param idHousing The id of the house
     * @param idOwner The id of the house owner
     */
    public HousingOffer(long beginDate, long endDate, long idHousing, long idOwner) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.idHousing = idHousing;
        this.idOwner = idOwner;
        this.idGuest = -1;
    }

    /**
     * Return the timestamp of when the offer start
     * @return <long> timestamp
     */
    public long getBeginTimestamp() {
        return this.beginDate;
    }
    /**
     * Return the timestamp of when the offer start
     * @return <Date> Date
     */
    public Date getBeginDate() {
        return new Date(this.beginDate);
    }
    /**
     * Return the timestamp of when the offer end
     * @return <long> timestamp
     */
    public long getEndTimestamp() {
        return this.endDate;
    }
    /**
     * Return the date of when the offer finish
     * @return <Date> Date
     */
    public Date getEndDate() {
        return new Date(this.endDate);
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
     * Set the guest id
     * @param <long> the guest id
     */
    public boolean isAvailable() {
        return this.idOwner == -1;
    }

}