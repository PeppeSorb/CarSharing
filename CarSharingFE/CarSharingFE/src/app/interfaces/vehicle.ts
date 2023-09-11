import { idModel } from "./idModel"

export interface Vehicle{
    id?: number,
    licensePlate?: string,
    idModel?: {
        idMod: string,
        categoryName?: string,
        makeAndModel?: string,
        bootCapacity?: number,
        averageConsumption?: number,
        forNewDrivers?: boolean,
        image?: string
    },
    country?: string,
    region?: string,
    city?: string,
    street?: string,
    houseNumber?: string,
    booked?: boolean,
    latitude?: number,
    longitude?: number
}
