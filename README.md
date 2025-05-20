# FleedGuard360 BACKEND Proyect

# 🔐 Generar clave privada y pública en formato PEM con OpenSSL

## 🛠 Requisitos
- Tener instalado **OpenSSL**
    - En Linux y macOS ya suele estar instalado.
    - En Windows puedes usar Git Bash o instalar OpenSSL desde [https://slproweb.com/products/Win32OpenSSL.html](https://slproweb.com/products/Win32OpenSSL.html)

---
## ✅ Paso 1: Crea una directorio (path)
En este directorio en una ruta segura en tu máquina crea un directorio o (carpeta) en el cual generar las claves de los 
siguientes pasos, para crear el directorio desde la terminal escriba el siguiente comando:

```bash
mkdir DIR_NAME
```
**Nota** Cambie ´DIR_NAME´ por el nombre de su predilección
Luego ubíquese (entre) en ese directorio, para esto puede usar el comando

```bash
cd DIR_NAME
```
Si desea consultar la ruta (o path) hasta este directorio escriba el siguiente comando:

```bash
pwd 
```

## ✅ Paso 2: Generar la clave privada (private_key.pem)

```bash
openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048

```

## ✅ Paso 3: Generar la clave pública (public_key.pem) a partir de la clave privada

```bash
openssl rsa -pubout -in private_key.pem -out public_key.pem

```

Esto extrae la clave pública desde `private_key.pem` y la guarda en `public_key.pem`.

## ✅ Paso 4: Genere las claves públicas y privadas para los refresh token key 

Para este paso repita los pasos 1 y 2 utilice los siguientes comandos:

```bash
openssl genpkey -algorithm RSA -out refresh_private_key.pem -pkeyopt rsa_keygen_bits:2048

```

```bash
openssl rsa -pubout -in refresh_private_key.pem -out refresh_public_key.pem

```

## Verifica los archivos (Opcional)

```bash
# Ver el contenido de la clave privada
cat private_key.pem

# Ver el contenido de la clave pública
cat public_key.pem

```

## 📂 Resultado ------------ 
Tendrás dos archivos: 
* `private_key.pem` → Clave privada 
* `public_key.pem` → Clave pública 
* 
* Ambos están en formato PEM (Base64 con cabeceras tipo `-----BEGIN...`).

## 🔐 Seguridad

* Guarda `private_key.pem` en un lugar **seguro** y no lo subas al repositorio. 
* Puedes usar variables de entorno para que tu aplicación lo lea desde una ruta segura.

Para garantizar estos criterios de seguridad debes crear un archivo `.env` dentro de la carpeta resources del 
proyecto. Adicional a esto incluye parámateros como el host de la base de datos, el puerto de conexión y 
en resumen, todas tus credenciales para que el backend y la db estén conectadas, tu archivo `.env`.
Una vez creado el archivo copie y pegue la siguiente sección cambiando el contenido por sus credenciales

```
DB_HOST=your_host
DB_PORT=your_port
DATA_BASE=your_db_name
DB_USERNAME=your_db_username
DB_PWD=your_password
PATH_PRIVATE=/ruta/al/archivo/private_key.pem
PATH_PUBLIC=/ruta/al/archivo/public_key.pem
PATH_REFRESH_PRIVATE=/ruta/al/archivo/refresh_private_key.pem
PATH_REFRESH_PUBLIC=/ruta/al/archivo/refresh_public_key.pem
```

**Nota** SIN EL ARCHIVO `.env` NO podrá ejecutar el proyecto

