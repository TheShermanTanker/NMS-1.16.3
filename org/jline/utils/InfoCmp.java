/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class InfoCmp
/*     */ {
/*  29 */   private static final Map<String, Object> CAPS = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Capability
/*     */   {
/*  37 */     auto_left_margin,
/*  38 */     auto_right_margin,
/*  39 */     back_color_erase,
/*  40 */     can_change,
/*  41 */     ceol_standout_glitch,
/*  42 */     col_addr_glitch,
/*  43 */     cpi_changes_res,
/*  44 */     cr_cancels_micro_mode,
/*  45 */     dest_tabs_magic_smso,
/*  46 */     eat_newline_glitch,
/*  47 */     erase_overstrike,
/*  48 */     generic_type,
/*  49 */     hard_copy,
/*  50 */     hard_cursor,
/*  51 */     has_meta_key,
/*  52 */     has_print_wheel,
/*  53 */     has_status_line,
/*  54 */     hue_lightness_saturation,
/*  55 */     insert_null_glitch,
/*  56 */     lpi_changes_res,
/*  57 */     memory_above,
/*  58 */     memory_below,
/*  59 */     move_insert_mode,
/*  60 */     move_standout_mode,
/*  61 */     needs_xon_xoff,
/*  62 */     no_esc_ctlc,
/*  63 */     no_pad_char,
/*  64 */     non_dest_scroll_region,
/*  65 */     non_rev_rmcup,
/*  66 */     over_strike,
/*  67 */     prtr_silent,
/*  68 */     row_addr_glitch,
/*  69 */     semi_auto_right_margin,
/*  70 */     status_line_esc_ok,
/*  71 */     tilde_glitch,
/*  72 */     transparent_underline,
/*  73 */     xon_xoff,
/*  74 */     columns,
/*  75 */     init_tabs,
/*  76 */     label_height,
/*  77 */     label_width,
/*  78 */     lines,
/*  79 */     lines_of_memory,
/*  80 */     magic_cookie_glitch,
/*  81 */     max_attributes,
/*  82 */     max_colors,
/*  83 */     max_pairs,
/*  84 */     maximum_windows,
/*  85 */     no_color_video,
/*  86 */     num_labels,
/*  87 */     padding_baud_rate,
/*  88 */     virtual_terminal,
/*  89 */     width_status_line,
/*  90 */     bit_image_entwining,
/*  91 */     bit_image_type,
/*  92 */     buffer_capacity,
/*  93 */     buttons,
/*  94 */     dot_horz_spacing,
/*  95 */     dot_vert_spacing,
/*  96 */     max_micro_address,
/*  97 */     max_micro_jump,
/*  98 */     micro_col_size,
/*  99 */     micro_line_size,
/* 100 */     number_of_pins,
/* 101 */     output_res_char,
/* 102 */     output_res_horz_inch,
/* 103 */     output_res_line,
/* 104 */     output_res_vert_inch,
/* 105 */     print_rate,
/* 106 */     wide_char_size,
/* 107 */     acs_chars,
/* 108 */     back_tab,
/* 109 */     bell,
/* 110 */     carriage_return,
/* 111 */     change_char_pitch,
/* 112 */     change_line_pitch,
/* 113 */     change_res_horz,
/* 114 */     change_res_vert,
/* 115 */     change_scroll_region,
/* 116 */     char_padding,
/* 117 */     clear_all_tabs,
/* 118 */     clear_margins,
/* 119 */     clear_screen,
/* 120 */     clr_bol,
/* 121 */     clr_eol,
/* 122 */     clr_eos,
/* 123 */     column_address,
/* 124 */     command_character,
/* 125 */     create_window,
/* 126 */     cursor_address,
/* 127 */     cursor_down,
/* 128 */     cursor_home,
/* 129 */     cursor_invisible,
/* 130 */     cursor_left,
/* 131 */     cursor_mem_address,
/* 132 */     cursor_normal,
/* 133 */     cursor_right,
/* 134 */     cursor_to_ll,
/* 135 */     cursor_up,
/* 136 */     cursor_visible,
/* 137 */     define_char,
/* 138 */     delete_character,
/* 139 */     delete_line,
/* 140 */     dial_phone,
/* 141 */     dis_status_line,
/* 142 */     display_clock,
/* 143 */     down_half_line,
/* 144 */     ena_acs,
/* 145 */     enter_alt_charset_mode,
/* 146 */     enter_am_mode,
/* 147 */     enter_blink_mode,
/* 148 */     enter_bold_mode,
/* 149 */     enter_ca_mode,
/* 150 */     enter_delete_mode,
/* 151 */     enter_dim_mode,
/* 152 */     enter_doublewide_mode,
/* 153 */     enter_draft_quality,
/* 154 */     enter_insert_mode,
/* 155 */     enter_italics_mode,
/* 156 */     enter_leftward_mode,
/* 157 */     enter_micro_mode,
/* 158 */     enter_near_letter_quality,
/* 159 */     enter_normal_quality,
/* 160 */     enter_protected_mode,
/* 161 */     enter_reverse_mode,
/* 162 */     enter_secure_mode,
/* 163 */     enter_shadow_mode,
/* 164 */     enter_standout_mode,
/* 165 */     enter_subscript_mode,
/* 166 */     enter_superscript_mode,
/* 167 */     enter_underline_mode,
/* 168 */     enter_upward_mode,
/* 169 */     enter_xon_mode,
/* 170 */     erase_chars,
/* 171 */     exit_alt_charset_mode,
/* 172 */     exit_am_mode,
/* 173 */     exit_attribute_mode,
/* 174 */     exit_ca_mode,
/* 175 */     exit_delete_mode,
/* 176 */     exit_doublewide_mode,
/* 177 */     exit_insert_mode,
/* 178 */     exit_italics_mode,
/* 179 */     exit_leftward_mode,
/* 180 */     exit_micro_mode,
/* 181 */     exit_shadow_mode,
/* 182 */     exit_standout_mode,
/* 183 */     exit_subscript_mode,
/* 184 */     exit_superscript_mode,
/* 185 */     exit_underline_mode,
/* 186 */     exit_upward_mode,
/* 187 */     exit_xon_mode,
/* 188 */     fixed_pause,
/* 189 */     flash_hook,
/* 190 */     flash_screen,
/* 191 */     form_feed,
/* 192 */     from_status_line,
/* 193 */     goto_window,
/* 194 */     hangup,
/* 195 */     init_1string,
/* 196 */     init_2string,
/* 197 */     init_3string,
/* 198 */     init_file,
/* 199 */     init_prog,
/* 200 */     initialize_color,
/* 201 */     initialize_pair,
/* 202 */     insert_character,
/* 203 */     insert_line,
/* 204 */     insert_padding,
/* 205 */     key_a1,
/* 206 */     key_a3,
/* 207 */     key_b2,
/* 208 */     key_backspace,
/* 209 */     key_beg,
/* 210 */     key_btab,
/* 211 */     key_c1,
/* 212 */     key_c3,
/* 213 */     key_cancel,
/* 214 */     key_catab,
/* 215 */     key_clear,
/* 216 */     key_close,
/* 217 */     key_command,
/* 218 */     key_copy,
/* 219 */     key_create,
/* 220 */     key_ctab,
/* 221 */     key_dc,
/* 222 */     key_dl,
/* 223 */     key_down,
/* 224 */     key_eic,
/* 225 */     key_end,
/* 226 */     key_enter,
/* 227 */     key_eol,
/* 228 */     key_eos,
/* 229 */     key_exit,
/* 230 */     key_f0,
/* 231 */     key_f1,
/* 232 */     key_f10,
/* 233 */     key_f11,
/* 234 */     key_f12,
/* 235 */     key_f13,
/* 236 */     key_f14,
/* 237 */     key_f15,
/* 238 */     key_f16,
/* 239 */     key_f17,
/* 240 */     key_f18,
/* 241 */     key_f19,
/* 242 */     key_f2,
/* 243 */     key_f20,
/* 244 */     key_f21,
/* 245 */     key_f22,
/* 246 */     key_f23,
/* 247 */     key_f24,
/* 248 */     key_f25,
/* 249 */     key_f26,
/* 250 */     key_f27,
/* 251 */     key_f28,
/* 252 */     key_f29,
/* 253 */     key_f3,
/* 254 */     key_f30,
/* 255 */     key_f31,
/* 256 */     key_f32,
/* 257 */     key_f33,
/* 258 */     key_f34,
/* 259 */     key_f35,
/* 260 */     key_f36,
/* 261 */     key_f37,
/* 262 */     key_f38,
/* 263 */     key_f39,
/* 264 */     key_f4,
/* 265 */     key_f40,
/* 266 */     key_f41,
/* 267 */     key_f42,
/* 268 */     key_f43,
/* 269 */     key_f44,
/* 270 */     key_f45,
/* 271 */     key_f46,
/* 272 */     key_f47,
/* 273 */     key_f48,
/* 274 */     key_f49,
/* 275 */     key_f5,
/* 276 */     key_f50,
/* 277 */     key_f51,
/* 278 */     key_f52,
/* 279 */     key_f53,
/* 280 */     key_f54,
/* 281 */     key_f55,
/* 282 */     key_f56,
/* 283 */     key_f57,
/* 284 */     key_f58,
/* 285 */     key_f59,
/* 286 */     key_f6,
/* 287 */     key_f60,
/* 288 */     key_f61,
/* 289 */     key_f62,
/* 290 */     key_f63,
/* 291 */     key_f7,
/* 292 */     key_f8,
/* 293 */     key_f9,
/* 294 */     key_find,
/* 295 */     key_help,
/* 296 */     key_home,
/* 297 */     key_ic,
/* 298 */     key_il,
/* 299 */     key_left,
/* 300 */     key_ll,
/* 301 */     key_mark,
/* 302 */     key_message,
/* 303 */     key_move,
/* 304 */     key_next,
/* 305 */     key_npage,
/* 306 */     key_open,
/* 307 */     key_options,
/* 308 */     key_ppage,
/* 309 */     key_previous,
/* 310 */     key_print,
/* 311 */     key_redo,
/* 312 */     key_reference,
/* 313 */     key_refresh,
/* 314 */     key_replace,
/* 315 */     key_restart,
/* 316 */     key_resume,
/* 317 */     key_right,
/* 318 */     key_save,
/* 319 */     key_sbeg,
/* 320 */     key_scancel,
/* 321 */     key_scommand,
/* 322 */     key_scopy,
/* 323 */     key_screate,
/* 324 */     key_sdc,
/* 325 */     key_sdl,
/* 326 */     key_select,
/* 327 */     key_send,
/* 328 */     key_seol,
/* 329 */     key_sexit,
/* 330 */     key_sf,
/* 331 */     key_sfind,
/* 332 */     key_shelp,
/* 333 */     key_shome,
/* 334 */     key_sic,
/* 335 */     key_sleft,
/* 336 */     key_smessage,
/* 337 */     key_smove,
/* 338 */     key_snext,
/* 339 */     key_soptions,
/* 340 */     key_sprevious,
/* 341 */     key_sprint,
/* 342 */     key_sr,
/* 343 */     key_sredo,
/* 344 */     key_sreplace,
/* 345 */     key_sright,
/* 346 */     key_srsume,
/* 347 */     key_ssave,
/* 348 */     key_ssuspend,
/* 349 */     key_stab,
/* 350 */     key_sundo,
/* 351 */     key_suspend,
/* 352 */     key_undo,
/* 353 */     key_up,
/* 354 */     keypad_local,
/* 355 */     keypad_xmit,
/* 356 */     lab_f0,
/* 357 */     lab_f1,
/* 358 */     lab_f10,
/* 359 */     lab_f2,
/* 360 */     lab_f3,
/* 361 */     lab_f4,
/* 362 */     lab_f5,
/* 363 */     lab_f6,
/* 364 */     lab_f7,
/* 365 */     lab_f8,
/* 366 */     lab_f9,
/* 367 */     label_format,
/* 368 */     label_off,
/* 369 */     label_on,
/* 370 */     meta_off,
/* 371 */     meta_on,
/* 372 */     micro_column_address,
/* 373 */     micro_down,
/* 374 */     micro_left,
/* 375 */     micro_right,
/* 376 */     micro_row_address,
/* 377 */     micro_up,
/* 378 */     newline,
/* 379 */     order_of_pins,
/* 380 */     orig_colors,
/* 381 */     orig_pair,
/* 382 */     pad_char,
/* 383 */     parm_dch,
/* 384 */     parm_delete_line,
/* 385 */     parm_down_cursor,
/* 386 */     parm_down_micro,
/* 387 */     parm_ich,
/* 388 */     parm_index,
/* 389 */     parm_insert_line,
/* 390 */     parm_left_cursor,
/* 391 */     parm_left_micro,
/* 392 */     parm_right_cursor,
/* 393 */     parm_right_micro,
/* 394 */     parm_rindex,
/* 395 */     parm_up_cursor,
/* 396 */     parm_up_micro,
/* 397 */     pkey_key,
/* 398 */     pkey_local,
/* 399 */     pkey_xmit,
/* 400 */     plab_norm,
/* 401 */     print_screen,
/* 402 */     prtr_non,
/* 403 */     prtr_off,
/* 404 */     prtr_on,
/* 405 */     pulse,
/* 406 */     quick_dial,
/* 407 */     remove_clock,
/* 408 */     repeat_char,
/* 409 */     req_for_input,
/* 410 */     reset_1string,
/* 411 */     reset_2string,
/* 412 */     reset_3string,
/* 413 */     reset_file,
/* 414 */     restore_cursor,
/* 415 */     row_address,
/* 416 */     save_cursor,
/* 417 */     scroll_forward,
/* 418 */     scroll_reverse,
/* 419 */     select_char_set,
/* 420 */     set_attributes,
/* 421 */     set_background,
/* 422 */     set_bottom_margin,
/* 423 */     set_bottom_margin_parm,
/* 424 */     set_clock,
/* 425 */     set_color_pair,
/* 426 */     set_foreground,
/* 427 */     set_left_margin,
/* 428 */     set_left_margin_parm,
/* 429 */     set_right_margin,
/* 430 */     set_right_margin_parm,
/* 431 */     set_tab,
/* 432 */     set_top_margin,
/* 433 */     set_top_margin_parm,
/* 434 */     set_window,
/* 435 */     start_bit_image,
/* 436 */     start_char_set_def,
/* 437 */     stop_bit_image,
/* 438 */     stop_char_set_def,
/* 439 */     subscript_characters,
/* 440 */     superscript_characters,
/* 441 */     tab,
/* 442 */     these_cause_cr,
/* 443 */     to_status_line,
/* 444 */     tone,
/* 445 */     underline_char,
/* 446 */     up_half_line,
/* 447 */     user0,
/* 448 */     user1,
/* 449 */     user2,
/* 450 */     user3,
/* 451 */     user4,
/* 452 */     user5,
/* 453 */     user6,
/* 454 */     user7,
/* 455 */     user8,
/* 456 */     user9,
/* 457 */     wait_tone,
/* 458 */     xoff_character,
/* 459 */     xon_character,
/* 460 */     zero_motion,
/* 461 */     alt_scancode_esc,
/* 462 */     bit_image_carriage_return,
/* 463 */     bit_image_newline,
/* 464 */     bit_image_repeat,
/* 465 */     char_set_names,
/* 466 */     code_set_init,
/* 467 */     color_names,
/* 468 */     define_bit_image_region,
/* 469 */     device_type,
/* 470 */     display_pc_char,
/* 471 */     end_bit_image_region,
/* 472 */     enter_pc_charset_mode,
/* 473 */     enter_scancode_mode,
/* 474 */     exit_pc_charset_mode,
/* 475 */     exit_scancode_mode,
/* 476 */     get_mouse,
/* 477 */     key_mouse,
/* 478 */     mouse_info,
/* 479 */     pc_term_options,
/* 480 */     pkey_plab,
/* 481 */     req_mouse_pos,
/* 482 */     scancode_escape,
/* 483 */     set0_des_seq,
/* 484 */     set1_des_seq,
/* 485 */     set2_des_seq,
/* 486 */     set3_des_seq,
/* 487 */     set_a_background,
/* 488 */     set_a_foreground,
/* 489 */     set_color_band,
/* 490 */     set_lr_margin,
/* 491 */     set_page_length,
/* 492 */     set_tb_margin,
/* 493 */     enter_horizontal_hl_mode,
/* 494 */     enter_left_hl_mode,
/* 495 */     enter_low_hl_mode,
/* 496 */     enter_right_hl_mode,
/* 497 */     enter_top_hl_mode,
/* 498 */     enter_vertical_hl_mode,
/* 499 */     set_a_attributes,
/* 500 */     set_pglen_inch;
/*     */ 
/*     */     
/*     */     public String[] getNames() {
/* 504 */       return (String[])InfoCmp.getCapabilitiesByName().entrySet().stream()
/* 505 */         .filter(e -> (e.getValue() == this))
/* 506 */         .map(Map.Entry::getValue)
/* 507 */         .toArray(x$0 -> new String[x$0]);
/*     */     }
/*     */     
/*     */     public static Capability byName(String name) {
/* 511 */       return InfoCmp.getCapabilitiesByName().get(name);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Map<String, Capability> getCapabilitiesByName() {
/* 516 */     Map<String, Capability> capabilities = new LinkedHashMap<>();
/* 517 */     try(InputStream is = InfoCmp.class.getResourceAsStream("capabilities.txt"); 
/* 518 */         BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
/* 519 */       br.lines().map(String::trim)
/* 520 */         .filter(s -> !s.startsWith("#"))
/* 521 */         .filter(s -> !s.isEmpty())
/* 522 */         .forEach(s -> {
/*     */             String[] names = s.split(", ");
/*     */             Capability cap = Enum.<Capability>valueOf(Capability.class, names[0]);
/*     */             capabilities.put(names[0], cap);
/*     */             capabilities.put(names[1], cap);
/*     */           });
/* 528 */       return capabilities;
/* 529 */     } catch (IOException e) {
/* 530 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setDefaultInfoCmp(String terminal, String caps) {
/* 535 */     CAPS.putIfAbsent(terminal, caps);
/*     */   }
/*     */   
/*     */   public static void setDefaultInfoCmp(String terminal, Supplier<String> caps) {
/* 539 */     CAPS.putIfAbsent(terminal, caps);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getInfoCmp(String terminal) throws IOException, InterruptedException {
/* 545 */     String caps = getLoadedInfoCmp(terminal);
/* 546 */     if (caps == null) {
/* 547 */       Process p = (new ProcessBuilder(new String[] { OSUtils.INFOCMP_COMMAND, terminal })).start();
/* 548 */       caps = ExecHelper.waitAndCapture(p);
/* 549 */       CAPS.put(terminal, caps);
/*     */     } 
/* 551 */     return caps;
/*     */   }
/*     */   
/*     */   public static String getLoadedInfoCmp(String terminal) {
/* 555 */     Object caps = CAPS.get(terminal);
/* 556 */     if (caps instanceof Supplier) {
/* 557 */       caps = ((Supplier)caps).get();
/*     */     }
/* 559 */     return (String)caps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void parseInfoCmp(String capabilities, Set<Capability> bools, Map<Capability, Integer> ints, Map<Capability, String> strings) {
/* 568 */     Map<String, Capability> capsByName = getCapabilitiesByName();
/* 569 */     String[] lines = capabilities.split("\n");
/* 570 */     for (int i = 1; i < lines.length; i++) {
/* 571 */       Matcher m = Pattern.compile("\\s*(([^,]|\\\\,)+)\\s*[,$]").matcher(lines[i]);
/* 572 */       while (m.find()) {
/* 573 */         String cap = m.group(1);
/* 574 */         if (cap.contains("#")) {
/* 575 */           int iVal, index = cap.indexOf('#');
/* 576 */           String key = cap.substring(0, index);
/* 577 */           String val = cap.substring(index + 1);
/*     */           
/* 579 */           if (val.startsWith("0x")) {
/* 580 */             iVal = Integer.parseInt(val.substring(2), 16);
/* 581 */           } else if (val.startsWith("0")) {
/* 582 */             iVal = Integer.parseInt(val.substring(1), 8);
/*     */           } else {
/* 584 */             iVal = Integer.parseInt(val);
/*     */           } 
/* 586 */           Capability capability = capsByName.get(key);
/* 587 */           if (capability != null)
/* 588 */             ints.put(capability, Integer.valueOf(iVal));  continue;
/*     */         } 
/* 590 */         if (cap.contains("=")) {
/* 591 */           int index = cap.indexOf('=');
/* 592 */           String key = cap.substring(0, index);
/* 593 */           String val = cap.substring(index + 1);
/* 594 */           Capability capability = capsByName.get(key);
/* 595 */           if (capability != null)
/* 596 */             strings.put(capability, val); 
/*     */           continue;
/*     */         } 
/* 599 */         Capability c = capsByName.get(cap);
/* 600 */         if (c != null) {
/* 601 */           bools.add(c);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static String loadDefaultInfoCmp(String name) {
/* 609 */     try(InputStream is = InfoCmp.class.getResourceAsStream(name + ".caps"); 
/* 610 */         BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
/* 611 */       return br.lines().collect(Collectors.joining("\n", "", "\n"));
/* 612 */     } catch (IOException e) {
/* 613 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   static {
/* 618 */     for (Iterator<String> iterator = Arrays.<String>asList(new String[] { "dumb", "ansi", "xterm", "xterm-256color", "windows", "windows-256color", "windows-conemu", "windows-vtp", "screen", "screen-256color" }).iterator(); iterator.hasNext(); ) { String s = iterator.next();
/*     */ 
/*     */       
/* 621 */       setDefaultInfoCmp(s, () -> loadDefaultInfoCmp(s)); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\InfoCmp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */