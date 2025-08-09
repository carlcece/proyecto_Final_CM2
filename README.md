
Descripcion: Este proyecto es una simulación de un ecosistema en una isla, modelado con Java. El objetivo principal es observar la dinámica de las poblaciones de diversas especies de animales y plantas, cómo interactúan entre sí y cómo sobreviven en un entorno con recursos limitados.

El sistema está diseñado con principios de programación orientada a objetos (POO), está construido en Java y utiliza Swing para su interfaz gráfica. La simulación implementa programación concurrente con la biblioteca java.util.concurrent, lo que permite que las acciones de los animales se ejecuten en paralelo de manera eficiente. En cuanto a la estructura del código, se aplican dos patrones de diseño clave: el Patrón Factory (AnimalFactory) para centralizar la creación de animales y el Patrón Singleton (Configuracion) para gestionar de forma global los parámetros del ecosistema. Todo esto se apoya en estructuras de datos como Map y CopyOnWriteArrayList, garantizando que la simulación sea segura en un entorno multi-hilo

Características Principales
- Ecosistema Completo: La simulación incluye una variedad de especies con roles específicos en la cadena alimenticia: herbívoros, omnívoros y carnívoros.
- Ciclo de Vida Realista: Los animales experimentan hambre (pérdida de peso), deshidratación (pérdida de agua) y un ciclo de reproducción probabilístico.
- Lógica de Inanición y Deshidratación: El peso del animal funciona como su reserva de energía. La pérdida de peso y la deshidratación se basan en valores fijos por ciclo, permitiendo un ajuste fino del equilibrio.
- Entorno Dinámico: El mapa es una cuadrícula de 50x10 celdas con diferentes tipos de terreno. Las plantas crecen y se consumen, y los animales se mueven de forma probabilística.
- Eventos Especiales: El ecosistema puede ser alterado con elementos como la "planta letal", que introduce un factor de riesgo en el entorno.
- Visualización en Tiempo Real: Cuenta con una interfaz gráfica (GUI) que muestra el estado de la isla, el historial de eventos y estadísticas detalladas del ciclo.

Cómo Ejecutar la Simulación
- Abre el proyecto en tu entorno de desarrollo (IDE) preferido, como IntelliJ IDEA o Eclipse.
- Asegúrate de que todos los archivos .java estén en sus respectivas carpetas (main, config, entidades, util, etc.).
- Ejecuta la clase SimulacionIsla.java para iniciar la interfaz gráfica.
- Usa los botones "Iniciar" y "Detener" en la GUI para controlar el flujo de la simulación.

Configuración y Registro
La clase Configuracion.java te permite ajustar todos los parámetros del ecosistema. Para seguir los eventos de la simulación, como nacimientos, muertes e interacciones, revisa los mensajes que se imprimen en la consola de tu IDE.




