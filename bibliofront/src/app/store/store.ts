import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import adresseReducer from "../store/slice/AdresseSlice";
import auteurReducer from "../store/slice/AuteurSlice";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";
import collectionReducer from "../store/slice/CollectionSlice";
import currentUsagerReducer from "../store/slice/CurrentUsagerSlice";
import genreReducer from "../store/slice/GenreSlice";
import livreResultatReducer from "../store/slice/LivreResultatSlice";
import livreReducer from "../store/slice/LivreSlice";
import rayonReducer from "../store/slice/RayonSlice";
import lieuReducer from "../store/slice/LieuSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
    livre: livreReducer,
    rayon: rayonReducer,
    genre: genreReducer,
    auteur: auteurReducer,
    livreResultat: livreResultatReducer,
    collection: collectionReducer,
    currentUsager: currentUsagerReducer,
    adresse: adresseReducer,
    lieu: lieuReducer,
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk = ThunkAction<void, RootState, unknown, Action>;
